<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원수정</title>
    <link rel="stylesheet" type="text/css" href="../css/updateUserStyles.css">
<style>
    /* 비활성화 상태의 버튼 스타일 */
    .btn-submit:disabled {
        background-color: gray;
        cursor: not-allowed; /* 비활성화 상태일 때 커서 모양 변경 */
    }
</style>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        const passwordField = document.getElementById('password');
        const confirmPasswordField = document.getElementById('confirm_password');
        const passwordFeedback = document.getElementById('password-feedback');
        const buttonSubmit = document.getElementById('btn-submit');

        const passwordPattern = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

        function validatePassword() {
            const password = passwordField.value;
            const confirmPassword = confirmPasswordField.value;

            if (!passwordPattern.test(password)) {
                passwordFeedback.innerHTML = '비밀번호는 8자 이상, 대문자, 소문자,<br>숫자 및 특수문자를 포함해야 합니다.';
                passwordFeedback.style.color = 'red';
                return false;
            } else if (password !== confirmPassword) {
                passwordFeedback.innerHTML = '비밀번호가 일치하지 않습니다.';
                passwordFeedback.style.color = 'red';
                return false;
            } else {
                passwordFeedback.textContent = '비밀번호가 유효하며 일치합니다.';
                passwordFeedback.style.color = 'green';
                return true;
            }
        }

        function validateForm() {
            const isPasswordValid = validatePassword();
            buttonSubmit.disabled = !isPasswordValid;
        }

        passwordField.addEventListener('input', validateForm);
        confirmPasswordField.addEventListener('input', validateForm);
    });
</script>
</head>
<body>
<%@ include file="/includes/header.jsp" %>
<div class="form-container">
    <h1 style="color:white;">회원수정</h1> <%--css 에 적용시 적용 안돼서 여기에 임시로 넣음--%>

    <% String message = (String) session.getAttribute("message"); %>
    <% if (message != null) { %>
    <div class="message-box">
        <p><%= message %>
        </p>
    </div>
    <% session.removeAttribute("message"); %>
    <% } %>

    <form action="updateUserOK.do" method="post">
        <div class="input-group">
            <label>이메일</label>
            <span>${user.email}</span> <!-- 여기에 실제 데이터 출력 -->
            <input type="hidden" name="email" value="${user.email}">
        </div>
        <div class="input-group">
            <label>이름</label>
            <span>${user.name}</span> <!-- 여기에 실제 데이터 출력 -->
        </div>
        <div class="input-group">
            <input type="password" name="password" placeholder="비밀번호" required class="full-width">
        </div>
        <div class="input-group">
            <input type="password" name="confirm_password" placeholder="비밀번호 확인" required class="full-width">
        </div>
        <div class="input-group">
            <input type="text" name="phone" placeholder="핸드폰 번호" required class="full-width">
        </div>
        <div class="input-group">
            <label>생년월일</label>
            <span>${user.birthday}</span> <!-- 여기에 실제 데이터 출력 -->
        </div>
        <button type="submit" id="btn-submit" class="btn-submit">수정하기</button>
        <button type="button" id="btn-cancel" class="btn-cancel" onclick="window.location.href='mainPage.jsp';">취소</button>
        <div id="password-feedback"></div>
    </form>
</div>
<%@ include file="/includes/footer.jsp" %>
</body>
</html>

