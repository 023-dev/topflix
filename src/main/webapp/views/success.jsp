<%--
  Created by IntelliJ IDEA.
  User: chundong
  Date: 2024. 7. 20.
  Time: 오후 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>영화 예약 - 결제완료</title>
    <link rel="stylesheet" href="../css/success.css">
</head>
<body>
<header>
    <%@ include file="/includes/header.jsp" %>
</header>
    <%--<div class="container">
    <div class="content-title">
        예매 완료
    </div>
    <div class="content">
        <h2>예매가 완료 되었습니다.</h2>
        <div class="info">
            <img src="${movieImage}" width="100%">
            <div>승인코드: <span>${pg_token}</span></div>
            <div>영화: ${movieTitle}</div>
            <div>극장: ${theaterName} / ${screenName}</div>
            <div>일시: ${showtime}</div>
            <div>인원: 일반 2명</div>
            <div>좌석: ${inputSeats}</div>
            <div>결제금액: ${totalAmount}원</div>
            <div>결제수단: KakaoPay</div>
        </div>
        <div class="buttons">
            <button onclick="location.href='main.do'">메인페이지</button>
            <button onclick="location.href='myPage.do'">예매페이지</button>
        </div>
    </div>
</div>--%>

<div class="ticket">
    <div class="ticket-header">
        <img src='${movieImage}' width="100%">
<%--        <div class="movie-rating">12</div>--%>
    </div>
    <div class="ticket-details">
        <h2>${movieTitle}</h2>
        <p><span class="important">${theaterName} / ${screenName}</span></p>
        <p>날짜: ${showtime}</p>
        <p>좌석: ${inputSeats}</p>
        <p>승인코드: <span>${pg_token}</span></p>
    </div>
    <div class="ticket-notes">
        <p>극장 이용 시 마스크 착용은 필수입니다. (미착용 시 입장 제한)</p>
        <p>입장 지연에 따른 관람 불편을 최소화하기 위해 본 영화는 10분 후 상영이 시작됩니다.</p>
        <p>영화 상영 시작 시간 15분 전까지 취소가 가능하며 캡처 화면은 입장이 제한될 수 있습니다.</p>
    </div>
</div>
<div class="next-container">
    <a href='/views/main.do'>
        메인페이지
    </a>
    <a href='/views/reservation.do'>
        예매내역확인
    </a>
</div>

<footer>
    <%@ include file="/includes/footer.jsp" %>
</footer>
</body>
</html>