import Slider from 'react-slick';
import React from 'react';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import BookCard from '~/components/cards/BookCard';
import AuthorCard from '~/components/cards/AuthorCard';
import Images from '~/assets/Images';

function Slick({ type = 1, bookdata, authordata }) {
    const settings1 = {
        dots: true,
        infinite: true,
        slidesToShow: 5,
        slidesToScroll: 1,
        autoplay: true,
        speed: 5000,
        responsive: [
            {
                breakpoint: 1200,
                settings: {
                    slidesToShow: 4,
                    slidesToScroll: 1,
                    infinite: true,
                    dots: true,
                },
            },
            {
                breakpoint: 992,
                settings: {
                    slidesToShow: 3,
                    slidesToScroll: 1,
                    initialSlide: 1,
                },
            },
            {
                breakpoint: 576,
                settings: {
                    slidesToShow: 2,
                    slidesToScroll: 1,
                },
            },
        ],
    };
    const settings2 = {
        dots: true,
        infinite: true,
        slidesToShow: 5,
        slidesToScroll: 5,
        speed: 1000,
        responsive: [
            {
                breakpoint: 1200,
                settings: {
                    slidesToShow: 4,
                    slidesToScroll: 4,
                    infinite: true,
                    dots: true,
                },
            },
            {
                breakpoint: 992,
                settings: {
                    slidesToShow: 3,
                    slidesToScroll: 3,
                    initialSlide: 1,
                },
            },
            {
                breakpoint: 576,
                settings: {
                    slidesToShow: 2,
                    slidesToScroll: 2,
                },
            },
        ],
    };
    if (type === 1)
        return (
            <Slider {...settings1}>
                {bookdata.map((e, id) => {
                    // return <div key={id}>e.name</div>;
                    return <BookCard key={id} id={e.id} name={e.name} author={e.author} price={e.price} img={e.img} />;
                })}
            </Slider>
        );
    if (type === 2)
        return (
            <Slider {...settings2}>
                {authordata.map((e, id) => {
                    // return <div key={id}>e.name</div>;
                    return <AuthorCard key={id} id={e.id} name={e.name} type={e.description} img={e.img} />;
                })}
            </Slider>
        );
}

export default Slick;
