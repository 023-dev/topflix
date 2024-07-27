<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <link rel="stylesheet" type="text/css" href="../css/signUpStyles.css">
    <style>
        /* 비활성화 상태의 버튼 스타일 */
        .btn-submit:disabled {
            background-color: gray;
            cursor: not-allowed; /* 비활성화 상태일 때 커서 모양 변경 */
        }
    </style>
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            const emailField = document.getElementById('email');
            const passwordField = document.getElementById('password');
            const confirmPasswordField = document.getElementById('confirm_password');
            const passwordFeedback = document.getElementById('password-feedback');
            const emailFeedback = document.getElementById('email-feedback');
            const buttonSubmit = document.getElementById('btn-submit');

            const passwordPattern = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
            const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

            function validateEmail() {
                const email = emailField.value;
                if (!emailPattern.test(email)) {
                    emailFeedback.textContent = '유효하지 않은 이메일 형식입니다.';
                    emailFeedback.style.color = 'red';
                    return false;
                } else {
                    emailFeedback.textContent = '유효한 이메일 형식입니다.';
                    emailFeedback.style.color = 'green';
                    return true;
                }
            }

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
                const isEmailValid = validateEmail();
                const isPasswordValid = validatePassword();
                buttonSubmit.disabled = !(isEmailValid && isPasswordValid);
            }

            emailField.addEventListener('input', validateForm);
            passwordField.addEventListener('input', validateForm);
            confirmPasswordField.addEventListener('input', validateForm);
        });
    </script>
</head>
<body>
<%@ include file="/includes/header.jsp" %>
<div class="form-container">
    <h1 style="color:white;">회원가입</h1>  <%--css 에 적용시 적용 안돼서 여기에 임시로 넣음--%>
    <form action="signUpOK.do" method="post">
        <div class="input-group">
            <input type="text" name="name" placeholder="이름" required class="full-width">
        </div>
        <div class="input-group">
            <input type="text" id="email" name="email" placeholder="이메일" required>
            <button type="button" class="btn-duplicate">중복 확인</button>
        </div>
        <div class="input-group">
            <input type="password" id="password" name="password" placeholder="비밀번호" required class="full-width">
        </div>
        <div class="input-group">
            <input type="password" id="confirm_password" name="confirm_password" placeholder="비밀번호 확인" required
                   class="full-width">
        </div>
        <div class="input-group">
            <input type="text" name="phone" placeholder="핸드폰 번호" required class="full-width">
        </div>
        <div class="input-group">
            <input type="date" name="birthdate" placeholder="생년월일" required class="full-width">
        </div>
        <button type="submit" id="btn-submit" class="btn-submit">가입하기</button>
        <div id="password-feedback"></div>
        <div id="email-feedback"></div>
    </form>
</div>
<%@ include file="/includes/footer.jsp" %>
</body>
</html>
