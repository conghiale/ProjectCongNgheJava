import styles from './Cart.scss';
import { useState, useEffect } from 'react';
import { useSelector } from 'react-redux';
import { Row, Col } from 'react-bootstrap';
import { MailOutlined, SettingOutlined } from '@ant-design/icons';
import React from 'react';
import { Button, Table, Form, Input, Modal } from 'antd';
import Images from '~/assets/Images';
import Cookies from 'js-cookie';
import axios from 'axios';
const port = 8080;
const { TextArea } = Input;
function Cart() {
    const context = useSelector((state) => state.Language);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [select, setSelect] = useState([]);
    const onFinish = (values) => {
        console.log('Received values of form: ', values);
    };
    let [total, setTotal] = useState(0);
    let [data, setData] = useState([]);
    const [change, setChange] = useState(false);
    useEffect(() => {
        let token = Cookies.get('bookmatetoken');
        if (token) {
            axios
                .get('http://localhost:' + port + '/api/users/user', {
                    headers: { 'Content-Type': 'application/json', Authorization: 'Bearer ' + token },
                })
                .then((data) => {
                    console.log(data.data.user.userProducts);
                    let temp = data.data.user.userProducts.map((e) => {
                        return {
                            key: e.product.id,
                            title: e.product.name,
                            img: Images.sachtemp,
                            cost: e.cost,
                            quantity: e.quantity,
                        };
                    });
                    console.log(temp);
                    setData(temp);
                });
        }
    }, [change]);
    const showModal = () => {
        setIsModalOpen(true);
    };
    const handleOk = () => {
        let token = Cookies.get('bookmatetoken');
        axios
            .post(
                'http://localhost:' + port + '/api/orders',
                { idProducts: select },
                {
                    headers: { 'Content-Type': 'application/json', Authorization: 'Bearer ' + token },
                },
            )
            .then((data) => {
                window.location.href = '/profile';
            });
        setIsModalOpen(false);
    };
    const handleCancel = () => {
        setIsModalOpen(false);
    };

    const [selectedRowKeys, setSelectedRowKeys] = useState([]);
    const onSelectChange = async (newSelectedRowKeys) => {
        setSelectedRowKeys(newSelectedRowKeys);
        let temp = await data.reduce((total, item) => {
            if (newSelectedRowKeys.includes(item.key)) return total + item.quantity * item.cost;
            else return total;
        }, 0);
        setSelect(newSelectedRowKeys);
        setTotal(temp);
    };
    const rowSelection = {
        selectedRowKeys,
        onChange: onSelectChange,
    };
    const hasSelected = selectedRowKeys.length > 0;
    const columns = [
        {
            key: 'action',
            render: (_, record) => (
                <div className="cart_product">
                    <div className="cart_product_img">
                        <img src={record.img}></img>
                    </div>

                    <div className="cart_product_title">
                        <h5>{record.title}</h5>
                    </div>
                    <span>
                        {context.quantity}:{' '}
                        <input
                            onChange={(elem) => {
                                let token = Cookies.get('bookmatetoken');
                                let path = '';
                                if (elem.target.value === '0') {
                                    path = 'http://localhost:' + port + '/api/users/cart/' + record.key;
                                    axios
                                        .delete(path, {
                                            headers: {
                                                'Content-Type': 'application/json',
                                                Authorization: 'Bearer ' + token,
                                            },
                                        })
                                        .then((data) => {
                                            setChange(true);
                                        });
                                } else {
                                    if (record.quantity < elem.target.value)
                                        path = 'http://localhost:' + port + '/api/users/cart/' + record.key;
                                    else {
                                        path =
                                            'http://localhost:' +
                                            port +
                                            '/api/users/cart/' +
                                            record.key +
                                            '?fluctuation=-1';
                                    }
                                    axios
                                        .post(
                                            path,
                                            {},
                                            {
                                                headers: {
                                                    'Content-Type': 'application/json',
                                                    Authorization: 'Bearer ' + token,
                                                },
                                            },
                                        )
                                        .then((data) => {
                                            setChange(true);
                                        });
                                }
                            }}
                            className="cart_quantity"
                            type="number"
                            min={0}
                            defaultValue={record.quantity}
                        ></input>
                    </span>
                    <div className="cart_product_price">
                        {context.price}: {record.cost.toLocaleString('en-US')}đ
                    </div>
                </div>
            ),
            width: 150,
        },
    ];
    return (
        <div className="cart_wrapper_outer">
            <Row className="cart_wrapper">
                <Col lg="3" md="4" className="cart_leftbar">
                    <div className="cart_content">
                        <h2 className="cart_title">{context.summary}</h2>
                        <span>{context.transFee}: 0đ</span>
                        <h5 className="history_order_price">
                            {context.total}: {total.toLocaleString('en-US')}đ
                        </h5>
                        <Form
                            onFinish={onFinish}
                            style={{
                                width: '100%',
                            }}
                        >
                            <Form.Item shouldUpdate>
                                {() => (
                                    <Button type="primary" htmlType="submit" onClick={showModal}>
                                        {context.Order}
                                    </Button>
                                )}
                            </Form.Item>
                        </Form>
                    </div>
                </Col>
                <Col lg="9" md="8" className="cart_rightbar">
                    <div className="cart_list">
                        <Table
                            columns={columns}
                            rowSelection={rowSelection}
                            dataSource={data}
                            style={{ width: '100%' }}
                        ></Table>
                        {/* <Table striped bordered className="cart_table_list">
                            <h4>Giỏ hàng</h4>
                            {dataTemp.map((el, ind) => {
                                return (
                                    <tr key={ind}>
                                        <td className="cart_product">
                                            <div className="cart_product_img">
                                                <img src={el.img}></img>
                                            </div>

                                            <div className="cart_product_title">
                                                <h5>{el.title}</h5>
                                            </div>
                                            <span>
                                                Số lượng:{' '}
                                                <input
                                                    className="cart_quantity"
                                                    type="number"
                                                    min={1}
                                                    defaultValue={el.quantity}
                                                ></input>
                                            </span>
                                            <div className="cart_product_price">
                                                Giá: {el.cost.toLocaleString('en-US')}đ
                                            </div>
                                        </td>
                                    </tr>
                                );
                            })}
                        </Table> */}
                    </div>
                </Col>
            </Row>
            <Modal title="Xác nhận" open={isModalOpen} onOk={handleOk} onCancel={handleCancel}>
                <p>Sau khi đặt hàng sẽ không thể hủy!</p>
            </Modal>
        </div>
    );
}

export default Cart;
