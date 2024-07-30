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
    <%@ include file="/includes/header.jsp" %>
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
                <div class="movie-item"></div>
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
                        <c:choose>
                            <c:when test="${empty sessionScope.userEmail}">
                                <button class="btn custom-button" onclick="alert('로그인이 필요한 기능입니다.')" type="button">예매하기</button>
                            </c:when>
                            <c:otherwise>
                                <button class="btn custom-button" onclick="location.href='ticketPage.do?title=${movie.movieTitle}'">예매하기</button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </c:forEach>
            </div>
        </section>
    </div>
</main>
<footer>
    <%@ include file="/includes/footer.jsp" %>
</footer>
<%--<script src="../js/chart.js"></script>--%>
</body>
</html>
