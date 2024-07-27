<%--
  Created by IntelliJ IDEA.
  User: chundong
  Date: 2024. 7. 22.
  Time: 오후 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>KakaoPay</title>
</head>
<body>
<h1>카카오페이 결제</h1>
<form action="kakaoPayAction.do" method="post">
    <label for="itemName">상품명:</label>
    <input type="text" id="itemName" name="itemName" required><br>
    <label for="quantity">수량:</label>
    <input type="number" id="quantity" name="quantity" required><br>
    <label for="totalAmount">총 금액:</label>
    <input type="number" id="totalAmount" name="totalAmount" required><br>
    <button type="submit">결제하기</button>
</form>
</body>
</html>
