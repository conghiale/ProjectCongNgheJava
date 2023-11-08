import Images from '~/assets/Images';
import styles from './Author.scss';
import classNames from 'classnames';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import BookCard from '~/components/cards/BookCard';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import { useState, useEffect } from 'react';

const port = 8080;

const cx = classNames.bind(styles);
const data = {
    book: [
        // { name: 'Chậm mà chill', author: 'Nguyễn Nhật Ánh', price: 108000, img: Images.sachtemp },
        // { name: 'Chậm mà chill', author: 'Nguyễn Nhật Ánh', price: 108000, img: Images.sachtemp },
        // { name: 'Chậm mà chill', author: 'Nguyễn Nhật Ánh', price: 108000, img: Images.sachtemp },
        // { name: 'Chậm mà chill', author: 'Nguyễn Nhật Ánh', price: 108000, img: Images.sachtemp },
        // { name: 'Chậm mà chill', author: 'Nguyễn Nhật Ánh', price: 108000, img: Images.sachtemp },
        // { name: 'Chậm mà chill', author: 'Nguyễn Nhật Ánh', price: 108000, img: Images.sachtemp },
        // { name: 'Chậm mà chill', author: 'Nguyễn Nhật Ánh', price: 108000, img: Images.sachtemp },
    ],
};
function Author() {
    const { id } = useParams();
    const [author, setAuthor] = useState({ name: '', dob: '', description: '' });
    const [list, setProducts] = useState([]);
    useEffect(() => {
        axios
            .get('http://localhost:' + port + '/api/authors/' + id, {
                headers: { 'Content-Type': 'application/json' },
            })
            .then((data) => {
                setAuthor(data.data.author);
            });
    }, []);
    useEffect(() => {
        axios
            .get('http://localhost:' + port + '/api/products/author?id=' + id, {
                headers: { 'Content-Type': 'application/json' },
            })
            .then((data) => {
                if (data.data && data.data.products) setProducts(data.data.products);
            });
    }, []);
    return (
        <Row className={cx('search_wrapper')}>
            <Col xl={3} className={cx('search_wrapper_detail')}>
                <div className={cx('author_profile')}>
                    <img src={Images.authortemp} alt="Author" className={cx('img_avatar')}></img>
                    <h4 style={{ marginBottom: 5 }}>{author.name}</h4>
                    <p>{author.dob}</p>
                    <span>{author.description}</span>
                </div>
            </Col>
            <Col xl={9} className={cx('search_wrapper_result')}>
                <div className={cx('search_result')}>
                    {list.map((e, i) => {
                        return (
                            <BookCard
                                key={e.id}
                                id={e.id}
                                name={e.name}
                                author={e.author.name}
                                authorID={e.author.id}
                                price={e.price}
                                img={Images.sachtemp}
                                width="true"
                            />
                        );
                    })}
                </div>
            </Col>
        </Row>
    );
}

export default Author;
