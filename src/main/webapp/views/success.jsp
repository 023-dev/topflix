<%--
  Created by IntelliJ IDEA.
  User: chundong
  Date: 2024. 7. 20.
  Time: 오후 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Payment Success</title>
</head>
<body>
<h2>Payment Successful!</h2>
<p>Your payment was completed successfully.</p>
승인코드 : ${pg_token}<br>
movieTitle : ${movieTitle}<br>
quantity : ${quantity}<br>
totalAmount : ${totalAmount}<br>
<button onclick="location.href='mainPage.do'">mainPage</button>
<button onclick="location.href='myPage.do'">myPage</button>
</body>
</html>