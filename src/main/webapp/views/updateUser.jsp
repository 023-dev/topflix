<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원수정</title>
<%--    <link rel="stylesheet" type="text/css" href="../css/updateUserStyles.css">--%>
    <style>
        body {
            background-color: #000;
            color: white;
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
        }
        .form-container {
            background-color: #333;
            padding: 30px;
            border-radius: 10px;
            width: 400px;
            box-shadow: 0px 0px 15px rgba(0,0,0,0.5);
            display: flex;
            flex-direction: column;
        }
        .form-container h1 {
            text-align: center;
            margin-bottom: 20px;
        }
        .input-group {
            margin-bottom: 15px;
        }
        .input-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        .input-group input {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 5px;
            background-color: #fff;
            font-size: 16px;
            box-sizing: border-box;
        }
        .input-group span {
            display: block;
            padding: 10px;
            background-color: #555;
            border-radius: 5px;
            font-size: 16px;
            margin-bottom: 15px;
        }
        .button-group {
            display: flex;
            justify-content: space-between;
        }
        .button-group button {
            width: 48%;
            padding: 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        .btn-submit {
            background-color: #e50914;
            color: white;
        }
        .btn-cancel {
            background-color: #555;
            color: white;
        }
        .btn-submit:hover {
            background-color: #d40813;
        }
        .btn-cancel:hover {
            background-color: #444;
        }
    </style>
</head>
<body>
<%@ include file="/includes/header.jsp" %>
<div class="form-container">
    <h1 style="color:white;">회원수정</h1>  <%--css 에 적용시 적용 안돼서 여기에 임시로 넣음--%>
    <form action="updateUserOK.do" method="post">
        <div class="input-group">
            <label>이메일</label>
            <span>${user.email}</span> <!-- 여기에 실제 데이터 출력 -->
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
<%--        <input type="hidden" id="birthdate" name="birthdate" value="${user.birthdate}">--%>
        <button type="submit" id="btn-submit" class="btn-submit">수정하기</button>
        <button type="button" id="btn-cancel" class="btn-cancel" onclick="window.location.href='mainPage.jsp';">취소
        </button>
    </form>
</div>
<%@ include file="/includes/footer.jsp" %>
</body>
</html>

