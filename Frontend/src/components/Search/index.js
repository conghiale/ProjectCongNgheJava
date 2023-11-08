import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';
import { useSelector } from 'react-redux';

import styles from './search.scss';
import classNames from 'classnames';
import { useState, useEffect } from 'react';

const cx = classNames.bind(styles);

function Search() {
    let [keyword, setKeyWord] = useState('');
    useEffect(() => {
        const params = new URLSearchParams(window.location.search);
        const keyword = params.get('keyword');
        console.log(keyword);
        setKeyWord(keyword);
    }, []);
    const context = useSelector((state) => state.Language);
    return (
        <div className={cx('wrapper')}>
            <form className={cx('form')}>
                <input type="text" placeholder={context.search} id="search" defaultValue={keyword}></input>
                <button
                    type="button"
                    className={cx('search')}
                    onClick={() => {
                        window.location.href = '/search?keyword=' + document.getElementById('search').value;
                    }}
                >
                    <FontAwesomeIcon icon={faMagnifyingGlass} />
                </button>
            </form>
        </div>
    );
}

export default Search;
