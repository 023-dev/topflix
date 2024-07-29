<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <link rel="stylesheet" type="text/css" href="../css/signUpStyles.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
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
            const phoneField = document.getElementById('phone');
            const confirmPasswordField = document.getElementById('confirm_password');
            const passwordFeedback = document.getElementById('password-feedback');
            const emailFeedback = document.getElementById('email-feedback');
            const buttonSubmit = document.getElementById('btn-submit');
            const buttonDuplicate = document.getElementById('checkButton');

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
            function formatPhoneNumber(event){
                const input = event.target.value;
                const cleaned = ('' + input).replace(/\D/g, '');
                const match = cleaned.match(/^(\d{0,3})(\d{0,4})(\d{0,4})$/);
                let formatted = '';

                if (match) {
                    if (match[1]) {
                        formatted = match[1];
                    }
                    if (match[2]) {
                        formatted += '-'+ match[2];
                    }
                    if (match[3]) {
                        formatted += '-' + match[3];
                    }
                }

                event.target.value = formatted;
                document.getElementById('formattedNumber').textContent = formatted;
            }
            function checkEmailDuplication() {
                const email = emailField.value;

                if (validateEmail()) {
                    fetch('checkEmail.do?email=' + encodeURIComponent(email))
                        .then(response => response.json())
                        .then(data => {
                            if (data.status === "success") {
                                alert("사용가능한 이메일입니다.")
                            } else {
                                alert("중복된 이메일 입니다.")
                                emailField.value = ''; // 이메일 입력 필드 비우기
                            }
                        })
                        .catch(error => {
                            alert("오류가 발생했습니다.")
                        });
                }
            }


            function validateForm() {
                const isEmailValid = validateEmail();
                const isPasswordValid = validatePassword();
                buttonSubmit.disabled = !(isEmailValid && isPasswordValid);
            }

            emailField.addEventListener('input', validateForm);
            passwordField.addEventListener('input', validateForm);
            phoneField.addEventListener('input', formatPhoneNumber);
            confirmPasswordField.addEventListener('input', validateForm);
            buttonDuplicate.addEventListener('click', checkEmailDuplication); // 중복 확인 버튼 클릭 시 검사

        });
    </script>
</head>
<body>
<%@ include file="/includes/header.jsp" %>
<div class="form-container">
    <h1 style="color:white;">회원가입</h1> <%--css 에 적용시 적용 안돼서 여기에 임시로 넣음--%>
    <form action="signUpOK.do" method="post">
        <div class="input-group">
            <input type="text" name="name" placeholder="이름" required class="full-width">
        </div>
        <div class="input-group">
            <input type="text" id="email" name="email" placeholder="이메일" required>
            <button type="button" id="checkButton" class="btn-duplicate">중복 확인</button>
        </div>
        <div class="input-group">
            <input type="password" id="password" name="password" placeholder="비밀번호" required class="full-width">
            <i class="fas fa-eye togglePassword"></i>
        </div>
        <div class="input-group">
            <input type="password" id="confirm_password" name="confirm_password" placeholder="비밀번호 확인" required
                   class="full-width">
            <i class="fas fa-eye togglePassword"></i>
        </div>
        <div class="input-group">
            <input type="text" id="phone" name="phone" placeholder="핸드폰 번호" required class="full-width">
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
