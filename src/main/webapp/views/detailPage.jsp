<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>${movie.movieTitle} - 상세화면</title>
	<link rel="stylesheet" href="../css/detail.css">
</head>
<body>
<header>
	<%@ include file="../includes/header.jsp" %>
</header>
<main>
	<div class="movie-banner">
		<img src="${movie.movieStillCut}" onclick="location.href='detailPage.do?title=${movie.movieTitle}'" alt="${movie.movieTitle} 포스터" style="background-image: linear-gradient(to bottom, rgba(0, 0, 0, 0) 0%, rgba(0, 0, 0, 1) 100%)">
		<div class="banner-content">
			<div class="title">
				<a href="detailPage.do?title=${movie.movieTitle}"><h1>${movie.movieTitle}</h1></a>
				<div class="buttons">

					<c:choose>
						<c:when test="${empty sessionScope.userEmail}">
							<button class="btn custom-button" onclick="alert('로그인이 필요한 기능입니다.')" type="button">예매하기</button>
						</c:when>
						<c:otherwise>
							<button class="btn custom-button" onclick="location.href='ticketPage.do?title=${movie.movieTitle}'">예매하기</button>
						</c:otherwise>
					</c:choose>
					<button class="btn red" onclick="location.href='ticketPage.do?title=${movie.movieTitle}'">예매</button>
					<button class="btn black" onclick="location.href='wish.do?title=${movie.movieTitle}'">저장</button>
				</div>
			</div>
			<div class="info">
				<div class="status">
					<span>관람 시청 등급 ALL</span>
					<span>${movie.movieReleaseDate} 개봉</span>
					<span>예매율 ${movie.movieReservationRate}</span>
					<span>흥행율 ${movie.movieEggGage}</span>
				</div>
				<p class="description">${movie.movieStory}</p>
				<div class="additional-info">
					<p><strong>감독 :</strong> ${movie.movieDirector}</p>
					<p><strong>배우 :</strong> ${movie.movieActor}</p>
					<p><strong>장르 :</strong> ${movie.movieGenre}</p>
					<p><strong>기본 정보 :</strong> ${movie.movieRunningtime}</p>
				</div>
			</div>
		</div>
	</div>
</main>
<footer>
	<%@include file="../includes/footer.jsp"%>
</footer>
</body>
</html>