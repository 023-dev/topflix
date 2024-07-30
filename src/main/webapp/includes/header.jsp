<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding-top: 50px; /* 헤더 높이만큼 패딩 추가 */
        }

        .header-container {
            display: flex;
            justify-content: center;
            width: 100%;
            box-sizing: border-box;
            position: fixed;
            top: 0;
            left: 0;
            right: 0;
            z-index: 1000; /* 다른 요소 위에 위치하도록 z-index 설정 */
            background-color: #000; /* 배경색 추가 */
            transition: background-color 0.3s ease;
        }

        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 100%;
            padding: 0; /* 패딩 제거 */
            box-sizing: border-box;
        }

        .header.scrolled {
            background-image: linear-gradient(to right, #111111, #333333); /* 스크롤 시 변경될 배경 그라데이션 */
            padding: 1px 0; /* 스크롤 시 패딩 조정 (상단,하단/좌측,우측) */
        }

        .logo {
            margin-left: 0; /* 로고 왼쪽 마진 제거 */
        }

        .logo img {
            height: 60px; /* 로고 크기 조정 */
            width: auto; /* 비율을 유지하며 너비 자동 조정 */
        }

        .menu {
            display: flex;
            align-items: center;
            margin-right: 20px; /* 메뉴 오른쪽 마진 제거 */
        }

        .menu div {
            margin: 0 10px; /* 메뉴 아이템 간의 간격을 조정 (간격을 10px로 줄임) */
        }

        .menu a {
            color: white;
            text-decoration: none;
            font-size: 16px;
            margin-right: inherit;
        }

        .menu a:hover {
            text-decoration: none;
        }

        @media screen and (max-width: 768px) {
            .header {
                flex-direction: column;
                align-items: flex-start;
            }

            .menu {
                flex-direction: column;
                width: 100%;
                margin-left: 0; /* 작은 화면에서는 왼쪽 마진 제거 */
            }

            .menu div {
                margin: 0px 0;
                width: 100%;
                text-align: center;
            }

            .menu a {
                font-size: 14px;
            }

            .login-btn {
                padding: 6px 10px;
                font-size: 14px;
                margin-right: 0; /* 작은 화면에서는 오른쪽 마진 제거 */
            }
        }
    </style>
</head>
<body>
<div class="header-container">
    <div class="header" id="header">
        <div class="logo">
            <a href="main.do"><img src="../images/logo.png" alt="Topflix Logo"></a>
        </div>
        <div class="menu">
            <c:choose>
                <c:when test="${empty sessionScope.userEmail}">
                    <a href="signIn.do">로그인</a>
                </c:when>
                <c:otherwise>
                    <a href="signOut.do">로그아웃</a>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${empty sessionScope.userEmail}">
                    <a href="signUp.do">회원가입</a>
                </c:when>
                <c:otherwise>
                    <a href="myPage.do">마이페이지</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
<script>
    window.addEventListener('scroll', function() {
        var header = document.getElementById('header');
        if (window.scrollY > 50) { // 스크롤이 50px 이상 내려갔을 때
            header.classList.add('scrolled');
        } else {
            header.classList.remove('scrolled');
        }
    });
</script>
</body>
</html>