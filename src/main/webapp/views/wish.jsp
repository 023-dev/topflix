<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>저장한 영화 목록</title>
    <link rel="stylesheet" href="../css/wish.css">
</head>
<body>
<header>
    <%@ include file="../includes/header.jsp" %>
</header>
<div class="content-wrapper">
    <div class="content-container">
        <div class="container">
            <main>
                <h1>저장한 영화 목록</h1>
<%--                <div class="movie-list">--%>
<%--                    <c:forEach var="movie" items="${movieList}" varStatus="status">--%>
<%--                        <div class="movie-item">--%>
<%--                            <img src="${movie.movieImage}" alt="${movie.movieTitle}">--%>
<%--                            <span>${movie.movieTitle}</span>--%>
<%--                            <span>${movie.movieGenre}</span>--%>
<%--                            <span>${movie.movieReservationRate}</span>--%>
<%--                            <span>${movie.movieEggGage}</span>--%>
<%--                            <div class="button-container">--%>
<%--                                <button class="reserve-button" onclick="location.href='ticketPage.do?title=${movie.movieTitle}'" type="button">예매하기</button>--%>
<%--                                <button class="cancel-button" onclick="removeItem(event, ${wish.wishSeq})">저장 취소</button>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <c:if test="${!status.last}">--%>
<%--                            <div class="separator"></div>--%>
<%--                        </c:if>--%>
<%--                    </c:forEach>--%>
                <div class="movie-list">
                    <c:forEach var="i" begin="1" end="10">
                        <div class="movie-item">
                            <img src="../images/ex.jpg" alt="인사이드아웃${i}">
                            <span>인사이드아웃${i}</span>
                            <span>애니메이션</span>
                            <span>예매율 50%</span>
                            <span>흥행율 50%</span>
                            <div class="button-container">
                                <button class="reserve-button" onclick="location.href='ticketPage.do?title=${movie.movieTitle}'" type="button">예매하기</button>
                                <button class="cancel-button">저장 취소</button>
                            </div>
                        </div>
                        <c:if test="${i < 10}">
                            <div class="separator"></div>
                        </c:if>
                    </c:forEach>
                </div>
            </main>
        </div>
    </div>
</div>


<footer>
    <%@ include file="../includes/footer.jsp" %>
</footer>
</body>
</html>
