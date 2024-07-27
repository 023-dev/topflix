<%--
  Created by IntelliJ IDEA.
  User: chundong
  Date: 2024. 7. 20.
  Time: 오후 9:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Payment</title>
</head>
<body>
<h2>Payment Form</h2>
<form id="paymentForm" method="post">
    <label for="movieTitle">Item Name:</label>
    <input type="text" id="movieTitle" name="movieTitle"><br>
    <label for="quantity">Quantity:</label>
    <input type="number" id="quantity" name="quantity"><br>
    <label for="price">Price :</label>
    <input type="number" id="price" name="price"><br>
</form>
<button onclick="submitForm('kakaopay.do')">Pay with KakaoPay</button>
<button onclick="submitForm('naverpay.do')">Pay with NaverPay</button>
<script>
    function submitForm(action) {
        var form = document.getElementById('paymentForm');
        form.action = action;
        form.submit();
    }
</script>
</body>
</html>
