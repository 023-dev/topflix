<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Topflix 로그인</title>
    <link rel="stylesheet" type="text/css" href="../css/signInStyles.css">
</head>
<body>
    <%@ include file="/includes/header.jsp" %>
    <div class="container">
        <div class="login-box">
            <h1>로그인</h1>
            <form action="signInOK.do" method="post">
                <div class="textbox">
                    <input type="text" placeholder="이메일" name="email" required>
                </div>
                <div class="textbox">
                    <input type="password" placeholder="비밀번호" name="password" required>
                </div>
                <button type="submit" class="btn">로그인</button>
            </form>
            <div class="links">
                <a href="#">비밀번호 찾기</a> | <a href="#">아이디 찾기</a> | <a href="signUp.jsp">회원가입</a>
            </div>
        </div>
    </div>
    <% if (session.getAttribute("loginError") != null) { %>
    <script type="text/javascript">
        alert("<%= session.getAttribute("loginError") %>");
        <% session.removeAttribute("loginError"); %>  <!-- 메시지 중복 방지 -->
    </script>
    <% } %>

    <%@ include file="/includes/footer.jsp" %>
</body>
</html>
