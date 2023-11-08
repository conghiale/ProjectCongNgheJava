import styles from './BookCard.scss';
import classNames from 'classnames/bind';
import { toMoney } from '~/utils/price';
import { useSelector } from 'react-redux';

const cx = classNames.bind(styles);

function BookCard({ name, price, author, img, width, id, authorID }) {
    const context = useSelector((state) => state.Language);
    let wrapperClass = cx('BookCard_wrapper_outer');
    if (width) {
        wrapperClass = cx('BookCard_wrapper_outer', 'toWidth');
    }
    return (
        <div className={wrapperClass}>
            <div className={cx('BookCard_wrapper_inner')}>
                <div className={cx('BookCard_img')}>
                    <img src={img} alt={name} />
                </div>
                <div
                    className={cx('BookCard_title')}
                    onClick={() => {
                        window.location.href = '/book/' + id;
                    }}
                >
                    {name}
                </div>

                <div className={cx('BookCard_infor')}>
                    <div>
                        <div className={cx('Infor_title')}>{context.author}</div>
                        <div className={cx('Infor_content')}>{author}</div>
                    </div>

                    <div className="text-right">
                        <div className={cx('Infor_title')}>{context.price}</div>
                        <div className={cx('Infor_content')}>
                            {toMoney(price)}
                            <u>đ</u>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default BookCard;
