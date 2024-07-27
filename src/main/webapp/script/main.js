document.querySelectorAll('.custom-button').forEach(button => {
    button.style.backgroundColor = 'black'; // 초기 색상 설정
    button.addEventListener('mouseenter', () => {
        button.style.backgroundColor = 'red';
    });
    button.addEventListener('mouseleave', () => {
        button.style.backgroundColor = 'black';
    });
});

document.addEventListener('DOMContentLoaded', function () {
    const prevButton = document.querySelector('.carousel-control-prev');
    const nextButton = document.querySelector('.carousel-control-next');
    const movieCarousel = document.querySelector('.movie-carousel');
    const movieWidth = document.querySelector('.movie').offsetWidth + 20; // margin 포함
    let currentIndex = 0;
    const itemsToMove = 2; // 한 번에 이동할 항목 수

    prevButton.addEventListener('click', function () {
        if (currentIndex > 0) {
            currentIndex -= itemsToMove;
            if (currentIndex < 0) currentIndex = 0;
            movieCarousel.scrollTo({ left: movieWidth * currentIndex, behavior: 'smooth' });
        }
    });

    nextButton.addEventListener('click', function () {
        if (currentIndex < movieCarousel.children.length - itemsToMove) {
            currentIndex += itemsToMove;
            if (currentIndex > movieCarousel.children.length - itemsToMove) {
                currentIndex = movieCarousel.children.length - itemsToMove;
            }
            movieCarousel.scrollTo({ left: movieWidth * currentIndex, behavior: 'smooth' });
        }
    });
});
