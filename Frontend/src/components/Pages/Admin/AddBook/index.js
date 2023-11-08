import { PlusOutlined } from '@ant-design/icons';
import { Button, DatePicker, Form, Input, InputNumber, Upload, Modal } from 'antd';
import { useState, useEffect } from 'react';
const { TextArea } = Input;
const normFile = (e) => {
    if (Array.isArray(e)) {
        return e;
    }
    return e?.fileList;
};
function AddBook() {
    const [isModalOpen, setIsModalOpen] = useState(false);
    const showModal = () => {
        setIsModalOpen(true);
    };
    const handleOk = () => {
        setIsModalOpen(false);
    };
    const handleCancel = () => {
        setIsModalOpen(false);
    };
    const [form] = Form.useForm();

    const [, forceUpdate] = useState({});
    useEffect(() => {
        forceUpdate({});
    }, []);

    return (
        <>
            <Form
                form={form}
                labelCol={{
                    span: 5,
                }}
                wrapperCol={{
                    span: 14,
                }}
                layout="horizontal"
                style={{
                    width: '100%',
                }}
            >
                <Form.Item
                    label="Tên sách"
                    rules={[
                        {
                            required: true,
                            message: 'Vui lòng nhập tên sách',
                        },
                    ]}
                >
                    <Input />
                </Form.Item>
                <Form.Item
                    label="Mô tả ngắn"
                    rules={[
                        {
                            required: true,
                            message: 'Vui lòng nhập mô tả sách',
                        },
                    ]}
                >
                    <TextArea rows={3} />
                </Form.Item>
                <Form.Item label="Giá">
                    <InputNumber />
                </Form.Item>
                <Form.Item label="Ngày sản xuất">
                    <DatePicker placeholder="Chọn ngày" />
                </Form.Item>
                <Form.Item label="Nhà sản xuất">
                    <Input />
                </Form.Item>
                <Form.Item label="Tải ảnh lên" valuePropName="fileList" getValueFromEvent={normFile}>
                    <Upload action="/upload.do" listType="picture-card">
                        <div>
                            <PlusOutlined />
                            <div
                                style={{
                                    marginTop: 8,
                                }}
                            >
                                Chọn ảnh
                            </div>
                        </div>
                    </Upload>
                </Form.Item>

                <Form.Item shouldUpdate>
                    {() => (
                        <Button
                            type="primary"
                            htmlType="submit"
                            disabled={
                                !form.isFieldsTouched(true) ||
                                !!form.getFieldsError().filter(({ errors }) => errors.length).length
                            }
                            onClick={showModal}
                        >
                            Hoàn tất
                        </Button>
                    )}
                </Form.Item>
            </Form>
            <Modal title="Xác nhận" open={isModalOpen} onOk={handleOk} onCancel={handleCancel}>
                <p>Bạn có chắc chắn muốn thêm sản phẩm!</p>
            </Modal>
        </>
    );
}

export default AddBook;
