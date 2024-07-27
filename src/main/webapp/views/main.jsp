<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TOPFLIX</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/main.css">
</head>
<body>
<header>
    <%@ include file="../includes/header.jsp" %>
</header>
<main>
    <section class="main-banner">
        <div class="trailer-container" style="height: auto!important;">
            <div class="video-overlay"></div>
            <video width="100%" autoplay loop muted>
                <source src="https://adimg.cgv.co.kr/images/202406/Doraemon/0710_Doraemon_1080x608.mp4" type="video/mp4">
            </video>
        </div>
        <div class="banner-text">
            <h1>극장판 도라에몽: 진구의 지구 교향곡</h1>
            <p>여름의 시작은? 도라에몽!<br> 절찬상영중</p>
        </div>
    </section>
    <section class="movie-chart">
        <div class="movie-chart-container">
            <h2 class="movie-chart-title" >무비차트</h2>
            <a href="chartPage.do" style="color: white;text-decoration:none">더보기</a>
        </div>
        <div class="movie-carousel-container">
            <div class="movie-carousel">
                <c:forEach var="movie" items="${movies}">
                    <div class="movie">
                        <div class="movie-img-container" style="background-image: url('${movie.movieImage}');background-size: cover;">
                            <div class="button-overlay">
                                <div class="button-container">
                                    <button class="btn custom-button" onclick="location.href='detailPage.do?title=${movie.movieTitle}'" type="button">상세보기</button>
                                    <button class="btn custom-button" onclick="location.href='ticketPage.do?title=${movie.movieTitle}'" type="button">예매하기</button>
                                    <button class="btn custom-button" onclick="location.href='wishPage.do?title=${movie.movieTitle}'" type="button">저장하기</button>
                                </div>
                            </div>
                        </div>
                        <div class="movie-info">
                            <h3 style="font-size: 1rem;overflow: hidden">${movie.movieTitle}</h3>
                            <p>
                                <span class="label">예매율</span>
                                <span class="value"> ${movie.movieReservationRate}</span>
                                <span class="label">흥행율</span>
                                <span class="value">${movie.movieEggGage}</span>
                            </p>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <a class="carousel-control-prev" role="button">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" role="button">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
    </section>
</main>
<footer>
    <%@include file="../includes/footer.jsp"%>>
</footer>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="../script/main.js"></script>
</body>
</html>
