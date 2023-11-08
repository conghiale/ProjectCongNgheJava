import classNames from 'classnames/bind';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Images from '~/assets/Images';
import BookCard from '~/components/cards/BookCard';
import AuthorCard from '~/components/cards/AuthorCard';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faXmark } from '@fortawesome/free-solid-svg-icons';
import { useState, useEffect } from 'react';
import { useSelector } from 'react-redux';
import axios from 'axios';

import style from './Search.scss';
const port = 8080;

const cx = classNames.bind(style);

function Search() {
    let [category, setCategory] = useState([]);
    let [keyword, setKeyWord] = useState('');
    let [pricerange, setpricerange] = useState(0);
    let [sort, setSort] = useState('');
    let [bookResult, setbookResult] = useState([]);
    let [categoryId, setCategoryId] = useState(0);
    useEffect(() => {
        axios
            .get('http://localhost:' + port + '/api/categories?page=0&size=20', {
                headers: { 'Content-Type': 'application/json' },
            })
            .then((data) => {
                let t = data.data.categories.content.map((el) => {
                    return { id: el.id, name: el.name };
                });
                setCategory(t);
            });
    }, []);
    useEffect(() => {
        const params = new URLSearchParams(window.location.search);
        const keyword = params.get('keyword') || '';
        const sort = params.get('sort') || '';
        const pricerange = params.get('index') || '';
        const categoryId = params.get('categoryId') || '';
        axios
            .get(
                'http://localhost:' +
                    port +
                    '/api/products/search?page=1&size=20&keyword=' +
                    keyword +
                    '&sort=price,' +
                    sort +
                    '&index=' +
                    pricerange +
                    '&categoryId=' +
                    categoryId,
                {
                    headers: { 'Content-Type': 'application/json' },
                },
            )
            .then((data) => {
                if (data.data) setbookResult(data.data.products);
            });
        setKeyWord(keyword);
        setSort(sort);
        setpricerange(pricerange);
        setCategoryId(categoryId);
    }, []);

    const context = useSelector((state) => state.Language);
    let [search_list, setList] = useState({ sortby: 1, priceRange: 0, type: 0 });
    const setSortBy = (n) => {
        setList((pre) => {
            return { ...pre, sortby: n };
        });
    };
    const setPriceRange = (n) => {
        setList((pre) => {
            return { ...pre, priceRange: n };
        });
    };
    const setType = (n) => {
        setList((pre) => {
            return { ...pre, type: n };
        });
    };
    return (
        <Row className={cx('search_wrapper')}>
            <Col xl={3} className={cx('search_wrapper_detail')}>
                <div className={cx('search_detail')}>
                    <div className={cx('search_detail_sortby')}>
                        <span>Sắp xếp theo:</span>
                        <select
                            name="search_sortby"
                            id="search_sortby"
                            onChange={(e) => {
                                window.location.href =
                                    '/search?keyword=' +
                                    keyword +
                                    '&sort=' +
                                    e.target.value +
                                    '&index=' +
                                    pricerange +
                                    '&categoryId=' +
                                    categoryId;
                            }}
                            value={sort}
                        >
                            <option value=""></option>
                            <option value="asc" defaultChecked={sort === 'asc'}>
                                Giá tăng dần
                            </option>
                            <option value="desc" defaultChecked={sort === 'desc'}>
                                Giá giảm dần
                            </option>
                        </select>
                    </div>
                    <div className={cx('search_detail_pricerange')}>
                        <span>Giá từ:</span>
                        <select
                            name="search_pricerange"
                            id="search_pricerange"
                            value={pricerange}
                            onChange={(e) => {
                                window.location.href =
                                    '/search?keyword=' +
                                    keyword +
                                    '&sort=' +
                                    sort +
                                    '&index=' +
                                    e.target.value +
                                    '&categoryId=' +
                                    categoryId;
                            }}
                        >
                            <option value=""></option>
                            <option value="1">Dưới 100.000đ</option>
                            <option value="2">từ 100.000đ đến 300.000đ</option>
                            <option value="3">từ 300.000đ đến 600.000đ</option>
                            <option value="4">Trên 600.000đ</option>
                        </select>
                    </div>
                    <div className={cx('search_detail_type')}>
                        <span>Thể loại:</span>
                        <select
                            name="search_type"
                            id="search_type"
                            value={categoryId}
                            onChange={(e) => {
                                window.location.href =
                                    '/search?keyword=' +
                                    keyword +
                                    '&sort=' +
                                    sort +
                                    '&index=' +
                                    pricerange +
                                    '&categoryId=' +
                                    e.target.value;
                            }}
                        >
                            <option value=""></option>
                            {category.map((e, id) => {
                                return (
                                    <option key={id} value={e.id}>
                                        {e.name}
                                    </option>
                                );
                            })}
                        </select>
                    </div>
                </div>
                <div className={cx('search_infor')}>
                    <div className={cx('search_infor_detail')}></div>
                </div>
            </Col>
            <Col xl={9} className={cx('search_wrapper_result')}>
                <div className={cx('search_result')}>
                    <h3 className={cx('search_title')}>{context.bookResultTitle}</h3>

                    {bookResult.map((e, i) => {
                        return (
                            <BookCard
                                key={e.id}
                                id={e.id}
                                name={e.name}
                                author={e.author.name}
                                price={e.price}
                                authorID={e.author.id}
                                img={Images.sachtemp}
                                width="true"
                            />
                        );
                    })}
                    {/* <h3 className={cx('search_title')}>{context.authorResultTitle}</h3> */}
                    {/* {data.author.map((e, i) => {
                        return <AuthorCard key={i} name={e.name} type={e.type} img={e.img} width="true" />;
                    })} */}
                </div>
            </Col>
        </Row>
    );
}

export default Search;
