import styles from './Login.scss';
import classNames from 'classnames/bind';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Images from '~/assets/Images';
import { LockOutlined, UserOutlined } from '@ant-design/icons';
import { Button, Form, Input } from 'antd';
import { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import Cookies from 'js-cookie';
import axios from 'axios';

const cx = classNames.bind(styles);
const port = 8080;

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
function Login() {
    const [form] = Form.useForm();
    const [, forceUpdate] = useState({});
    const context = useSelector((state) => state.Language);
    // To disable submit button at the beginning.
    useEffect(() => {
        forceUpdate({});
    }, []);

    const handleFormSubmit = () => {
        const username = form.getFieldValue('username');
        const password = form.getFieldValue('password');
        console.log(username + '-' + password);
        axios
            .post('http://localhost:' + port + '/api/auth/login', { username: username, password: password })
            .then(async (data) => {
                if (data.status === 200) {
                    console.log(data);
                    toast.success('Đăng nhập thành công');
                    await Cookies.set('bookmatetoken', data.data.access_token, {
                        expires: 30,
                    });
                    await axios
                        .get('http://localhost:' + port + '/api/users/user', {
                            headers: {
                                'Content-Type': 'application/json',
                                Authorization: 'Bearer ' + data.data.access_token,
                            },
                        })
                        .then((data) => {
                            if (data.data.user.roles[0].name === 'ROLE_ADMIN') {
                                setTimeout(() => {
                                    window.location.href = '/admin';
                                }, 5000);
                            } else {
                                setTimeout(() => {
                                    window.location.href = '/';
                                }, 5000);
                            }
                        });
                    // setTimeout(() => {
                    //     window.location.href = '/';
                    // }, 5000);
                } else {
                    toast.error('Tên đăng nhập hoặc mật khẩu không đúng');
                }
            })
            .catch((e) => {
                toast.error('Lỗi: ' + e);
            });
    };
    return (
        <div className="login_card_wrapper">
            <ToastContainer></ToastContainer>
            <div className="login_card">
                <div className="image_login">
                    <img src={Images.login} alt="Login" />
                </div>
                <div className={cx('login_content')}>
                    <h2>{context.login}</h2>
                    <Form
                        form={form}
                        className="form_login"
                        name="horizontal_login"
                        layout="inline"
                        {...formItemLayout}
                        onFinish={handleFormSubmit}
                    >
                        <Form.Item
                            label={context.username}
                            name="username"
                            rules={[
                                {
                                    required: true,
                                    message: context.requiredUsername,
                                },
                            ]}
                        >
                            <Input
                                prefix={<UserOutlined className="site-form-item-icon" />}
                                placeholder={context.username}
                            />
                        </Form.Item>
                        <Form.Item
                            name="password"
                            label={context.password}
                            rules={[
                                {
                                    required: true,
                                    message: context.requiredPassword,
                                },
                            ]}
                        >
                            <Input
                                prefix={<LockOutlined className="site-form-item-icon" />}
                                type="password"
                                placeholder={context.password}
                            />
                        </Form.Item>
                        <Form.Item shouldUpdate style={{ width: '100%' }}>
                            {() => (
                                <Button
                                    style={{ float: 'right' }}
                                    type="primary"
                                    htmlType="submit"
                                    disabled={
                                        !form.isFieldsTouched(true) ||
                                        !!form.getFieldsError().filter(({ errors }) => errors.length).length
                                    }
                                >
                                    {context.login}
                                </Button>
                            )}
                        </Form.Item>
                        <Form.Item shouldUpdate style={{ width: '100%', textAlign: 'right' }}>
                            {context.lan === 'vi' ? 'Hoặc' : 'Or'} <a href="/register">{context.register}</a>
                        </Form.Item>
                    </Form>
                </div>
            </div>
        </div>
    );
}

export default Login;
