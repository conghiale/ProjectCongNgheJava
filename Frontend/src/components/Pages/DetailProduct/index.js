import styles from './DetailProduct.scss';
import classNames from 'classnames/bind';
import { Row, Col, Table } from 'react-bootstrap';
import Images from '~/assets/Images';
import { InputNumber, Button, Space } from 'antd';
import { useState, useEffect } from 'react';
import { ShoppingCartOutlined } from '@ant-design/icons';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
import Cookies from 'js-cookie';
import { useSelector } from 'react-redux';

const cx = classNames.bind(styles);
const onChange = (value) => {
    console.log('changed', value);
};
const port = 8080;

function DetailProduct() {
    const context = useSelector((state) => state.Language);
    const { id } = useParams();
    let [data, setData] = useState({
        name: '',
        title: '',
        price: 0,
        publicationDate: '',
        publisher: '',
        author: { id: 0, name: '' },
    });
    useEffect(() => {
        axios
            .get('http://localhost:' + port + '/api/products/' + id, {
                headers: { 'Content-Type': 'application/json' },
            })
            .then((data) => {
                setData(data.data.product);
            });
    }, []);
    const [loadings, setLoadings] = useState([]);
    const enterLoading = (index) => {
        setLoadings((prevLoadings) => {
            const newLoadings = [...prevLoadings];
            newLoadings[index] = true;
            return newLoadings;
        });
    };
    return (
        <Row className="detaild_product_wrapper">
            <ToastContainer></ToastContainer>
            <Col lg="4" md="5" className="detailBook_image">
                <img src={Images.sachtemp}></img>
            </Col>
            <Col lg="8" md="7" className="detailBook_detail">
                <h1>{data.name}</h1>
                <span>{data.title}</span>
                <Table>
                    <tr>
                        <td>
                            {context.pupDate}: <span className="text_bold">{data.publicationDate || ''}</span>
                        </td>
                        <td>
                            {context.author}: <span className="text_bold">{data.author.name}</span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            {context.pupCompany}: <span className="text_bold">{data.publisher}</span>
                        </td>
                        <td>
                            {context.corverForm}: <span className="text_bold">Bìa mềm</span>
                        </td>
                    </tr>
                </Table>
                <h2 className="detail_product_price">{data.price.toLocaleString()}đ</h2>
                <Table>
                    <tr>
                        <td>{context.DeliveryTime}:</td>
                        <td>{context.DeliveryTimeRe}</td>
                    </tr>
                    <tr>
                        <td>{context.ReturnPolicy}:</td>
                        <td>{context.ReturnPolicyRe}</td>
                    </tr>
                </Table>
                <div>
                    <span>{context.quantity}: </span>
                    <InputNumber min={1} max={10} defaultValue={1} onChange={onChange} />
                </div>
                <Button
                    type="primary"
                    icon={<ShoppingCartOutlined style={{ fontSize: '24px' }} />}
                    loading={loadings[1]}
                    onClick={() => {
                        enterLoading(1);
                        let token = Cookies.get('bookmatetoken');
                        axios
                            .post(
                                'http://localhost:' + port + '/api/users/cart/' + id,
                                {},
                                {
                                    headers: { 'Content-Type': 'application/json', Authorization: 'Bearer ' + token },
                                },
                            )
                            .then((data) => {
                                if (data.status === 200) {
                                    toast.success('Thêm vào giỏ hàng thành công');
                                    setLoadings((prevLoadings) => {
                                        const newLoadings = [...prevLoadings];
                                        newLoadings[1] = false;
                                        return newLoadings;
                                    });
                                } else {
                                    setLoadings((prevLoadings) => {
                                        const newLoadings = [...prevLoadings];
                                        newLoadings[1] = false;
                                        return newLoadings;
                                    });
                                    toast.error('Thêm vào giỏ hàng không thành công');
                                }
                            })
                            .catch((err) => {
                                window.location.href = '/login';
                            });
                    }}
                >
                    {context.addToCart}
                </Button>
            </Col>
        </Row>
    );
}

export default DetailProduct;
