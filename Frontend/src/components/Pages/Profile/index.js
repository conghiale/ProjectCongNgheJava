import styles from './Profile.scss';
import { Button, Checkbox, Form, Input, Select } from 'antd';
import { useState, useEffect } from 'react';
import { useSelector } from 'react-redux';
import { Row, Col } from 'react-bootstrap';
import { MailOutlined, SettingOutlined } from '@ant-design/icons';
import React from 'react';
import { Menu, Space, InputNumber } from 'antd';
import { Table } from 'react-bootstrap';
import Images from '~/assets/Images';
import Cookies from 'js-cookie';
import axios from 'axios';

function getItem(label, key, icon, children, type) {
    return {
        key,
        icon,
        children,
        label,
        type,
    };
}

const items = [getItem('Profile', '0'), getItem('History Order', '1'), getItem('Log out', '2')];
const port = 8080;
const { Option } = Select;
const formItemLayout = {
    labelCol: {
        xs: {
            span: 24,
        },
        sm: {
            span: 8,
        },
    },
    wrapperCol: {
        xs: {
            span: 24,
        },
        sm: {
            span: 16,
        },
    },
};
const tailFormItemLayout = {
    wrapperCol: {
        xs: {
            span: 24,
            offset: 0,
        },
        sm: {
            span: 16,
            offset: 8,
        },
    },
};

function Profile() {
    const token = Cookies.get('bookmatetoken');
    let [tabAcc, setTab] = useState(0);
    const context = useSelector((state) => state.Language);
    const [form] = Form.useForm();
    const onFinish = (values) => {
        axios
            .put('http://localhost:' + port + '/api/users', values, {
                headers: { 'Content-Type': 'application/json', Authorization: 'Bearer ' + token },
            })
            .then((data) => {
                console.log(data);
            });
    };
    const [userForm, setUserForm] = useState(<></>);
    const [orders, setOrders] = useState([]);
    useEffect(() => {
        axios
            .get('http://localhost:' + port + '/api/users/order', {
                headers: { 'Content-Type': 'application/json', Authorization: 'Bearer ' + token },
            })
            .then((data) => {
                setOrders(data.data.orders);
            });
    }, []);
    useEffect(() => {
        if (token) {
            axios
                .get('http://localhost:' + port + '/api/users/user', {
                    headers: { 'Content-Type': 'application/json', Authorization: 'Bearer ' + token },
                })
                .then((data) => {
                    if (data.data && data.data.user) {
                        let t = data.data.user;
                        setUserForm(
                            <div className="profile_content">
                                <Form
                                    {...formItemLayout}
                                    form={form}
                                    name="register"
                                    onFinish={onFinish}
                                    style={{
                                        maxWidth: 600,
                                    }}
                                    scrollToFirstError
                                >
                                    <Form.Item
                                        name="name"
                                        label={context.name}
                                        initialValue={t.name}
                                        rules={[
                                            {
                                                required: true,
                                                message: context.requiredName,
                                                whitespace: true,
                                            },
                                        ]}
                                    >
                                        <Input className="profile_input" />
                                    </Form.Item>
                                    <Form.Item
                                        name="username"
                                        label={context.username}
                                        initialValue={t.username}
                                        rules={[
                                            {
                                                required: true,
                                                message: context.requiredUsername,
                                                whitespace: false,
                                            },
                                        ]}
                                    >
                                        <Input className="profile_input" />
                                    </Form.Item>

                                    <Form.Item
                                        name="email"
                                        initialValue={t.email}
                                        label="E-mail"
                                        rules={[
                                            {
                                                type: 'email',
                                                message: context.invalidEmail,
                                            },
                                            {
                                                required: true,
                                                message: context.requireEmail,
                                            },
                                        ]}
                                    >
                                        <Input className="profile_input" />
                                    </Form.Item>

                                    <Form.Item
                                        name="phone"
                                        initialValue={t.phone}
                                        label={context.phone}
                                        rules={[
                                            {
                                                required: true,
                                                message: context.requirePhone,
                                            },
                                        ]}
                                    >
                                        <Input className="profile_input" />
                                    </Form.Item>

                                    <Form.Item
                                        name="gender"
                                        initialValue={t.gender.toLowerCase()}
                                        label={context.gender}
                                        rules={[
                                            {
                                                required: true,
                                                message: context.requireGender,
                                            },
                                        ]}
                                    >
                                        <Select placeholder={context.selectGender} className="profile_input">
                                            <Option value="male">{context.male}</Option>
                                            <Option value="female">{context.female}</Option>
                                        </Select>
                                    </Form.Item>

                                    <Form.Item {...tailFormItemLayout}>
                                        <Button type="primary" htmlType="submit">
                                            {context.update}
                                        </Button>
                                    </Form.Item>
                                </Form>
                            </div>,
                        );
                        console.log({
                            name: t.name,
                            username: t.username,
                            email: t.email,
                            phone: t.phone,
                            gender: t.gender.toLowerCase(),
                        });
                    }
                });
        }
    }, []);

    const onClick = (e) => {
        if (e.key === '0') {
            setTab(0);
        } else if (e.key === '1') {
            setTab(1);
        } else if (e.key === '2') {
            Cookies.set('bookmatetoken', 't', {
                expires: 0,
            });
            window.location.href = '/';
        }
    };

    let el =
        tabAcc === 0 ? (
            userForm
        ) : (
            <div>
                {orders.map((e, id) => {
                    return (
                        <div className="history_order">
                            <Table striped bordered className="history_order_list">
                                <h6>Đơn hàng: #{e.id}</h6>
                                {e.detailOrders.map((element, id2) => {
                                    return (
                                        <tr key={id2}>
                                            <td className="history_order_product">
                                                <div className="history_order_img">
                                                    <img src={Images.sachtemp}></img>
                                                </div>

                                                <div>
                                                    <h5
                                                        style={{ cursor: 'pointer' }}
                                                        onClick={() => {
                                                            window.location.href = '/book/' + element.product.id;
                                                        }}
                                                    >
                                                        {element.product.name}
                                                    </h5>
                                                    <span>Tác giả: {element.product.author.name}</span>
                                                </div>

                                                <div className="history_order_product_price">
                                                    <span style={{ paddingRight: 20 }}>
                                                        Số lượng: {element.quantity}
                                                    </span>
                                                    Giá: {element.cost.toLocaleString('en-US')}đ
                                                </div>
                                            </td>
                                        </tr>
                                    );
                                })}
                                <h6 style={{ paddingTop: '10px' }}> Tổng :{e.cost.toLocaleString('en-US')}đ</h6>
                            </Table>
                        </div>
                    );
                })}
            </div>
        );

    return (
        <div className="profile_wrapper_outer">
            <Row className="profile_wrapper">
                <Col lg="3" md="4" className="profile_leftbar">
                    <div className="profile_content">
                        <h2 className="profile_title">Account</h2>
                        <div>
                            <Menu
                                onClick={onClick}
                                style={{
                                    width: '100%',
                                }}
                                defaultSelectedKeys={['0']}
                                defaultOpenKeys={['sub0']}
                                mode="inline"
                                items={items}
                            />
                        </div>
                    </div>
                </Col>
                <Col lg="9" md="8" className="profile_rightbar">
                    {el}
                </Col>
            </Row>
        </div>
    );
}

export default Profile;
