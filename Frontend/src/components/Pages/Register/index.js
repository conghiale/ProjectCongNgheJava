import styles from './Register.scss';
import classNames from 'classnames/bind';
import Images from '~/assets/Images';
import { Button, Checkbox, Form, Input, Select } from 'antd';
import { useState } from 'react';
import { useSelector } from 'react-redux';
import Cookies from 'js-cookie';
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';

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

function Register() {
    const context = useSelector((state) => state.Language);
    const [form] = Form.useForm();
    const onFinish = (values) => {
        console.log('Received values of form: ', values);
        axios
            .post('http://localhost:' + port + '/api/auth/register', values)
            .then(async (data) => {
                if (data.status === 200) {
                    console.log(data);
                    toast.success('Đăng ký thành công');
                    await Cookies.set('bookmatetoken', data.data.access_token, {
                        expires: 30,
                    });
                    setTimeout(() => {
                        window.location.href = '/';
                    }, 5000);
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
    const prefixSelector = (
        <Form.Item name="prefix" noStyle>
            <Select
                style={{
                    width: 70,
                }}
            >
                <Option value="84">+84</Option>
            </Select>
        </Form.Item>
    );
    return (
        <div className="login_card_wrapper">
            <ToastContainer></ToastContainer>
            <div className="login_card">
                <div className="image_login">
                    <img src={Images.login} alt="Login" />
                </div>
                <div className="login_content">
                    <h2>{context.register}</h2>
                    {/* <form>
                        <label for="username">Username:</label>
                        <input type="text" id="username" name="username" required />
                        <label for="password">Password:</label>
                        <input type="password" id="password" name="password" required />
                        <button type="submit">Log In</button>
                    </form> */}
                    <Form
                        {...formItemLayout}
                        form={form}
                        name="register"
                        onFinish={onFinish}
                        initialValues={{
                            prefix: '84',
                        }}
                        style={{
                            maxWidth: 600,
                        }}
                        scrollToFirstError
                    >
                        <Form.Item
                            name="name"
                            label={context.name}
                            tooltip={context.tooltipName}
                            rules={[
                                {
                                    required: true,
                                    message: context.requiredName,
                                    whitespace: true,
                                },
                            ]}
                        >
                            <Input />
                        </Form.Item>
                        <Form.Item
                            name="username"
                            label={context.username}
                            tooltip={context.tooltipUserName}
                            rules={[
                                {
                                    required: true,
                                    message: context.requiredUsername,
                                    whitespace: false,
                                },
                            ]}
                        >
                            <Input />
                        </Form.Item>

                        <Form.Item
                            name="email"
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
                            <Input />
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
                            hasFeedback
                        >
                            <Input.Password />
                        </Form.Item>

                        <Form.Item
                            name="confirm"
                            label={context.repeatPassword}
                            dependencies={['password']}
                            hasFeedback
                            rules={[
                                {
                                    required: true,
                                    message: context.requiredRepeatPassword,
                                },
                                ({ getFieldValue }) => ({
                                    validator(_, value) {
                                        if (!value || getFieldValue('password') === value) {
                                            return Promise.resolve();
                                        }
                                        return Promise.reject(new Error(context.failConfirmPassword));
                                    },
                                }),
                            ]}
                        >
                            <Input.Password />
                        </Form.Item>

                        <Form.Item
                            name="phone"
                            label={context.phone}
                            rules={[
                                {
                                    required: true,
                                    message: context.requirePhone,
                                },
                            ]}
                        >
                            <Input
                                addonBefore={prefixSelector}
                                style={{
                                    width: '100%',
                                }}
                            />
                        </Form.Item>

                        <Form.Item
                            name="gender"
                            label={context.gender}
                            rules={[
                                {
                                    required: true,
                                    message: context.requireGender,
                                },
                            ]}
                        >
                            <Select placeholder={context.selectGender}>
                                <Option value="male">{context.male}</Option>
                                <Option value="female">{context.female}</Option>
                            </Select>
                        </Form.Item>

                        <Form.Item
                            name="agreement"
                            valuePropName="checked"
                            rules={[
                                {
                                    validator: (_, value) =>
                                        value
                                            ? Promise.resolve()
                                            : Promise.reject(new Error(context.requiredAgreement)),
                                },
                            ]}
                            {...tailFormItemLayout}
                        >
                            <Checkbox>{context.agreement}</Checkbox>
                        </Form.Item>
                        <Form.Item {...tailFormItemLayout}>
                            <Button type="primary" htmlType="submit">
                                {context.register}
                            </Button>
                        </Form.Item>
                    </Form>
                </div>
            </div>
        </div>
    );
}

export default Register;
