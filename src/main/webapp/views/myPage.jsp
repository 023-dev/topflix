<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마이페이지</title>
    <link rel="stylesheet" type="text/css" href="../css/myPage.css">
</head>
<body>
<header>
    <%@ include file="../includes/header.jsp" %>
</header>
<div class="container">
    <h1>마이페이지</h1>
    <div class="profile">
        <div class="profile-item">
            <h2>${name}</h2>
            <p>${email}</p>
            <p>${phone}</p>
            <div class="action-item-01">
                <a href="updateUser.do" class="btn red">회원정보수정</a>
                <a href="deleteUser.do" class="btn grey">회원탈퇴</a>
            </div>
        </div>
        <div class="divider"></div>
        <div class="action-item">
            <p class="count">${reservationSize} 건</p>
            <a href="reservation.do" class="btn">예매내역</a>
        </div>
        <div class="divider"></div>
        <div class="action-item">
            <p class="count">${wishSize} 건</p>
            <a href="wish.do" class="btn">관심영화</a>
        </div>
<%--        <div class="divider"></div>--%>
<%--        <div class="action-item">--%>
<%--            <p class="count">0 건</p>--%>
<%--            <a href="#" class="btn">리뷰</a>--%>
<%--        </div>--%>
    </div>
</div>
<footer>
    <%@ include file="../includes/footer.jsp" %>
</footer>
</body>
</html>
