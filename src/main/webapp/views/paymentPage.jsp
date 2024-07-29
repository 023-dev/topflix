<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>좌석 선택 - 영화제목</title>
    <link rel="stylesheet" href="../css/payment.css">
</head>
<body>
<header>
    <div class="logo">TOPFLIX</div>
    <nav>
        <ul>
            <li><a href="#">HOME</a></li>
            <li><a href="#">MY PAGE</a></li>
        </ul>
        <button class="login-btn">로그인</button>
    </nav>
</header>
<main>
    <section class="booking-summary">
        <div class="movie-poster">
            <img src="${movie.movieImage}" alt="영화 포스터">
        </div>
        <div class="summary-info">
            <h3>${movie.movieTitle}</h3>
            <p>${movie.movieRating}</p>
            <p>2024.07.13(토) 13:10</p>
            <p>상영관: 1관 5층</p>
            <p>좌석가격: 12000원</p>
            <p id="seats">좌석: </p>
        </div>
        <form id="payment-form" action="payment.do" method="post" enctype="application/x-www-form-urlencoded">
            <input type="hidden" name="movieTitle" value=${movie.movieTitle}}>
            <input type="hidden" name="theaterName" value="홍대입구">
            <input type="hidden" name="showtime" value="2024.07.13(토) 13:10">
            <input type="hidden" name="screenName" value="1관">
            <input type="hidden" name="seatPrice" value="12000">
            <input type="hidden" name="seats" value=${seats}>
        </form>
        <button type="submit" form="payment-form" class="confirm-btn">결제하기</button>
    </section>
</main>
<footer>
    <div class="footer-content">
        <ul>
            <li><a href="#">회사소개</a></li>
            <li><a href="#">이용약관</a></li>
            <li><a href="#">개인정보처리방침</a></li>
            <li><a href="#">고객센터</a></li>
        </ul>
        <p>© 2024 TOPFLIX. All rights reserved.</p>
    </div>
</footer>
</body>
</html>
