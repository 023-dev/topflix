<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>저장한 영화 목록</title>
    <link rel="stylesheet" href="../css/like.css">
</head>
<body>
<%@ include file="e/header.jsp" %>

<div class="container">
    <div class="header2">저장한 영화 목록</div>
    <c:forEach>

    </c:forEach>
    <table class="movie-list">
        <tr>
            <th></th>
            <th>제목</th>
            <th>장르</th>
            <th>예매율</th>
            <th></th>
        </tr>
        <tr>
            <td><img src="https://upload.wikimedia.org/wikipedia/en/3/3d/Inside_Out_%282015_film%29_poster.jpg" alt="인사이드 아웃 2"></td>
            <td>인사이드 아웃 2</td>
            <td>애니메이션</td>
            <td>예매율 50%</td>
            <td class="actions">
                <button class="reserve-btn">예매하기</button>
                <button class="cancel-btn">저장 취소</button>
            </td>
        </tr>
        <tr>
            <td><img src="https://upload.wikimedia.org/wikipedia/en/f/fd/Escape_Plan_poster.jpg" alt="탈주"></td>
            <td>탈주</td>
            <td>액션</td>
            <td>예매율 50%</td>
            <td class="actions">
                <button class="reserve-btn">예매하기</button>
                <button class="cancel-btn">저장 취소</button>
            </td>
        </tr>
    </table>
</div>

</body>
</html>
