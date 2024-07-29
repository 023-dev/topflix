<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>예매 완료</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
        }
        .container {
            width: 50%;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            text-align: center;
        }
        .header {
            background-color: #333;
            color: #fff;
            padding: 10px;
            font-size: 1.2em;
        }
        .content {
            margin-top: 20px;
        }
        .movie-poster img {
            width: 100px;
        }
        .info {
            text-align: left;
            margin-top: 20px;
        }
        .info div {
            margin-bottom: 10px;
        }
        .buttons {
            margin-top: 20px;
        }
        .buttons button {
            padding: 10px 20px;
            margin: 5px;
            border: none;
            color: #fff;
            background-color: #e74c3c;
            cursor: pointer;
        }
        .buttons button:nth-child(2) {
            background-color: #3498db;
        }
        .buttons button:nth-child(3) {
            background-color: #2ecc71;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        예매 완료
    </div>
    <div class="content">
        <h2>예매가 완료 되었습니다.</h2>
        <div class="info">
            <div>예매번호: <span>0059-****-6696-***3</span></div>
            <div>영화: 작은 아씨들</div>
            <div>극장: CGV 영등포 / 1관(THX) 4층</div>
            <div>일시: 2020년 2월 29일 (토) 16:25 ~ 18:50</div>
            <div>인원: 일반 2명</div>
            <div>좌석: I14, I13</div>
            <div>결제금액: 24,000원</div>
            <div>결제수단: CGV 영화관람권 24,000원</div>
        </div>
        <div class="buttons">
            <button>예매정보 출력</button>
            <button>예매결과 SMS 발송</button>
            <button>예매확인/취소</button>
        </div>
    </div>
</div>
</body>
</html>
