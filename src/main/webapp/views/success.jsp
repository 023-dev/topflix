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
</header>
<div class="container">
    <div class="header">
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
            <button onclick="location.href='mainPage.do'">메인페이지</button>
            <button onclick="location.href='myPage.do'">예매페이지</button>
        </div>
    </div>
</div>
<footer>
</footer>
</body>
</html>