<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원탈퇴</title>
    <link rel="stylesheet" type="text/css" href="../css/withdrawUser.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
<%@ include file="/includes/header.jsp" %>

<div class="withdraw-container">
<h1>회원탈퇴</h1>
<p>정말 탈퇴하시겠습니까?</p>
<p>비밀번호를 한번 더 입력해 주세요</p>
<form action="withdrawUser.do" method="post">
    <div class = "input-wrapper">
    <input type="password" name="password" placeholder="비밀번호" required>
    <i class="fas fa-eye togglePassword"></i>
    </div>
    <button type="submit" class="btn-withdraw">탈퇴하기</button>
</form>
</div>
<% if (request.getAttribute("message") != null) { %>
<script type="text/javascript">
    alert("<%= request.getAttribute("message") %>");
    <% request.removeAttribute("message"); %>  <!-- 메시지 중복 방지 -->
</script>
<% } %>



<%@ include file="/includes/footer.jsp" %>

<!-- jQuery Library -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- Custom Script -->
<script>
    $(document).ready(function(){
        $('.togglePassword').on('click', function() {
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

</body>
</html>
