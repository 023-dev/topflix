<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Topflix 로그인</title>
    <link rel="stylesheet" type="text/css" href="../css/signIn.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
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
                <div class="input-wrapper">
                    <input type="password" placeholder="비밀번호" name="password" required>
                    <i class="fas fa-eye togglePassword"></i>
                </div>
            </div>
            <button type="submit" class="btn">로그인</button>
        </form>
        <div class="links">
            <a href="#">비밀번호 찾기</a>
            <a href="signUp.jsp">회원가입</a>
        </div>
    </div>
</div>
<!-- jQuery Library -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- Custom Script -->
<script>
    $(document).ready(function () {
        $('.togglePassword').on('click', function () {
            var input = $(this).prev('input');
            var icon = $(this);
            if (input.attr('type') === 'password') {
                input.attr('type', 'text');
                icon.attr('class', 'fas fa-eye-slash togglePassword');
            } else {
                input.attr('type', 'password');
                icon.attr('class', 'fas fa-eye togglePassword');
            }
        });
    });
</script>

<% if (session.getAttribute("loginError") != null) { %>
<script type="text/javascript">
    alert("<%= session.getAttribute("loginError") %>");
    <% session.removeAttribute("loginError"); %>  <!-- 메시지 중복 방지 -->
</script>
<% } %>
<%@ include file="/includes/footer.jsp" %>
</body>
</html>
