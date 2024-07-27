<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TOPFLIX</title>
    <link rel="stylesheet" href="../css/chart.css">
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
    <div class="movie-chart-container">
        <section class="movie-chart">
            <h2 id="movieChartBtn" class="tablink active-title" onclick="openTab('movie-chart')">무비차트</h2>
            <h2 id="upcomingMoviesBtn" class="tablink" onclick="openTab('upcoming-movies')">상영예정작</h2>
        </section>
        <section id="movie-chart" class="tabcontent" style="display: block;">
            <div class="movie-list">
                <c:forEach var="movie" items="${movies}">
                    <div class="movie-item">
                        <img src="${movie.movieImage}" alt="영화 포스터">
                        <div class="movie-info">
                            <h3>${movie.movieTitle}</h3>
                            <p>
                                <span class="label">예매율</span>
                                <span class="value">${movie.movieReservationRate}%</span>
                                <span class="label">흥행율</span>
                                <span class="value">${movie.movieEggGage}%</span>
                            </p>
                            <p>${movie.movieReleaseDate} 개봉</p>
                        </div>
                        <button class="btn custom-button" onclick="location.href='ticketPage.do?title=${movie.movieTitle}'">예매하기</button>
                    </div>
                </c:forEach>
            </div>
        </section>
        <section id="upcoming-movies" class="tabcontent" style="display: none;">
            <div class="movie-list">
                <c:forEach var="movie" items="${upcomingMovies}">
                    <div class="movie-item">
                        <img src="${movie.movieImage}" alt="영화 포스터">
                        <div class="movie-info">
                            <h3>${movie.movieTitle}</h3>
                            <p>
                                <span class="label">예매율</span>
                                <span class="value">${movie.movieReservationRate}%</span>
                                <span class="label">흥행율</span>
                                <span class="value">${movie.movieEggGage}%</span>
                            </p>
                            <p>${movie.movieReleaseDate} 개봉</p>
                        </div>
                        <button class="btn custom-button" onclick="location.href='ticketPage.do?title=${movie.movieTitle}'">예매하기</button>
                    </div>
                </c:forEach>
            </div>
        </section>
    </div>
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
<script src="../js/chart.js"></script>
</body>
</html>
