<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인 결과</title>
    <link rel="stylesheet" type="text/css" href="../css/styles.css">
    <%--    <link rel="stylesheet" type="text/css" href="../css/loginOK.css">--%>
</head>
<body>
<%@ include file="/includes/header.jsp" %>
<%
    PrintWriter out2 = response.getWriter();
    int result = (Integer) request.getAttribute("re");
    if (result == 1) {
        response.sendRedirect("mainPage.jsp");
    } else if (result == 0) {
        out2.print("<script>alert('암호가 잘못되었습니다.'); window.history.back();</script>");
    } else if (result == -1) {
        out2.print("<script>alert('존재하지 않는 아이디입니다.'); window.history.back();</script>");
    }
%>
<%@ include file="/includes/footer.jsp" %>
</body>
</html>
