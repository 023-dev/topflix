<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마이페이지</title>
    <link rel="stylesheet" type="text/css" href="../css/styles.css">
    <link rel="stylesheet" type="text/css" href="../css/mypageStyles.css">
</head>
<body>
<%@ include file="/includes/header.jsp" %>
<div class="container">
    <h1>마이페이지</h1>
    <div class="profile">
        <div class="profile-item">
            <h2>홍길동님</h2>
            <p>email@naver.com</p>
            <p>010-0000-0000</p>
            <a href="updateUser.do" class="btn">회원정보수정</a>
            <a href="withdrawUser.do" class="btn">회원 탈퇴</a>
        </div>
        <div class="action-item">
            <p class="count">2 건</p>
            <a href="#" class="btn">예약 내역</a>
        </div>
        <div class="action-item">
            <p class="count">2 건</p>
            <a href="#" class="btn">저장하기</a>
        </div>
        <div class="action-item">
            <p class="count">0 건</p>
            <a href="#" class="btn">리뷰</a>
        </div>
    </div>
</div>
<%@ include file="/includes/footer.jsp" %>
</body>
</html>
