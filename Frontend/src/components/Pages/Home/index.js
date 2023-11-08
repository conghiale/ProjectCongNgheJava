import Slider from '~/components/Slider';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Slick from '~/components/Slick';
import { useSelector } from 'react-redux';
import styles from './Home.scss';
import classNames from 'classnames/bind';
import { useState, useEffect } from 'react';
import axios from 'axios';
import Images from '~/assets/Images';
const cx = classNames.bind(styles);
const port = 8080;

function Home() {
    let [bestBook, setBestBook] = useState([]);
    let [newAuthor, setAuthor] = useState([]);
    useEffect(() => {
        axios
            .get('http://localhost:' + port + '/api/products?page=0&size=10', {
                headers: { 'Content-Type': 'application/json' },
            })
            .then((data) => {
                let t = data.data.products.map((el) => {
                    return {
                        id: el.id,
                        name: el.name,
                        author: el.author.name,
                        authorID: el.author.id,
                        price: el.price,
                        img: Images.sachtemp,
                    };
                });
                setBestBook(t);
            });
    }, []);
    useEffect(() => {
        axios
            .get('http://localhost:' + port + '/api/authors?page=0&size=8&sort=id,asc', {
                headers: { 'Content-Type': 'application/json' },
            })
            .then((data) => {
                console.log(data);
                let t = data.data.authors.content.map((el) => {
                    return {
                        id: el.id,
                        name: el.name,
                        description: el.description,
                        img: Images.authortemp,
                    };
                });
                setAuthor(t);
            });
    }, []);
    const context = useSelector((state) => state.Language);
    return (
        <div className={cx('Home_wrapper')}>
            <Row className={cx('Slider_wrapper')}>
                <Col xl={4} lg={4} className={cx('Slider_title')}>
                    <div className={cx('Slider_title_main')}>{context.slogan}</div>
                    <div className={cx('Slider_title_to')}>
                        <span>{context.let}</span>
                    </div>
                </Col>
                <Col xl={8} lg={8}>
                    <Slider className={cx('Slider_content')}></Slider>
                </Col>
            </Row>
            <div className={cx('Book_slick_wrapper')}>
                <div className={cx('Book_slick_title')}>
                    <h3>{context.bestseller}</h3>
                </div>
                <Slick type={1} bookdata={bestBook} />
            </div>
            <div className={cx('Book_slick_wrapper')}>
                <div className={cx('Book_slick_title')}>
                    <h3>{context.popAuthor}</h3>
                </div>
                <Slick type={2} authordata={newAuthor} />
            </div>
        </div>
    );
}

export default Home;
