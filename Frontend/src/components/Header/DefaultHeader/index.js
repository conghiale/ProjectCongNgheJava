import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import Images from '~/assets/Images';
import Search from '~/components/Search';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCartShopping } from '@fortawesome/free-solid-svg-icons';
import Cookies from 'js-cookie';
import { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import axios from 'axios';

import styles from './DefaultHeader.scss';
import classNames from 'classnames/bind';
import { toast } from 'react-toastify';

const cx = classNames.bind(styles);
const port = 8080;

function DefaultHeader() {
    let [list, setList] = useState([]);
    const dispatch = useDispatch();
    const context = useSelector((state) => state.Language);
    const language = useSelector((state) => state.Language.lan);
    const [lan, setLan] = useState(language);
    const [login, setLogin] = useState(false);
    let [Account, setAccount] = useState({});
    useEffect(() => {
        axios
            .get('http://localhost:' + port + '/api/categories?page=0&size=20', {
                headers: { 'Content-Type': 'application/json' },
            })
            .then((data) => {
                let t = data.data.categories.content.map((el) => {
                    return { id: el.id, name: el.name };
                });
                setList(t);
            });
    }, []);
    useEffect(() => {
        let t = Cookies.get('bookmatetoken');
        if (t) {
            axios
                .get('http://localhost:' + port + '/api/users/user', {
                    headers: { 'Content-Type': 'application/json', Authorization: 'Bearer ' + t },
                })
                .then((data) => {
                    if (data.data && data.data.user) {
                        setAccount(data.data.user);
                        setLogin(true);
                    }
                });
        }
    }, []);
    return (
        <Navbar expand="lg" className={cx('wrapper_header')} fixed="top">
            <Container className={cx('container')}>
                <Navbar.Brand href="/home" className={cx('brand')}>
                    <img src={Images.logo} alt="img" className={cx('logo')}></img>
                </Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                        <Search />
                    </Nav>
                    <Nav>
                        <NavDropdown title={context.type} id="dropdown-menu-align-end">
                            {list.map((e, id) => {
                                return (
                                    <NavDropdown.Item
                                        href={'/search?keyword=&sort=&index=&categoryId=' + e.id}
                                        key={id}
                                    >
                                        {e.name}
                                    </NavDropdown.Item>
                                );
                            })}
                        </NavDropdown>
                        <Nav.Link
                            onClick={() => {
                                if (login) {
                                    window.location.href = '/cart';
                                } else {
                                    window.location.href = '/login';
                                }
                            }}
                        >
                            <FontAwesomeIcon icon={faCartShopping} /> {context.cart}
                        </Nav.Link>
                    </Nav>

                    <Nav style={{ marginLeft: 20 }}>
                        {!login ? (
                            <Nav.Link href="/login" className={cx('login')}>
                                <div className={cx('btn_login')}>{context.login}</div>
                            </Nav.Link>
                        ) : (
                            <Nav.Link href="/profile">
                                Hello,{' '}
                                {Account.name === ''
                                    ? context.login
                                    : Account.name.split(' ')[Account.name.split(' ').length - 1]}
                            </Nav.Link>
                        )}
                        <NavDropdown title={lan} id="dropdown-menu-align-end">
                            <NavDropdown.Item
                                onClick={() => {
                                    dispatch({ type: 'TO_VI' });
                                    setLan('Vi');
                                }}
                            >
                                Vi
                            </NavDropdown.Item>
                            <NavDropdown.Item
                                onClick={() => {
                                    dispatch({ type: 'TO_EN' });
                                    setLan('En');
                                }}
                            >
                                En
                            </NavDropdown.Item>
                        </NavDropdown>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
}

export default DefaultHeader;
