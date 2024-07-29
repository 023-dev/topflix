<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>좌석 선택 - 영화제목</title>
    <link rel="stylesheet" href="../css/ticket.css">
    <script>
        function selectSeat(row, seat) {
            const selectedSeats = document.getElementById("selectedSeats");
            const seatString = row + seat;
            const selectedSeatsCount = selectedSeats.value.split(',').filter(s => s).length;
            console.log(seatString)
            if (selectedSeats.value.includes(seatString)) {
                selectedSeats.value = selectedSeats.value.replace(seatString + ',', '');
                document.getElementById(seatString).classList.remove('selected');
                document.getElementById(seatString).classList.add('available');
            } else {
                if(selectedSeatsCount >= 8){
                    alert("선택할 수 없습니다. 최대 8개의 좌석만 선택 가능합니다.");
                    return;
                }
                selectedSeats.value += seatString + ',';
                document.getElementById(seatString).classList.remove('available');
                document.getElementById(seatString).classList.add('selected');
                // document.getElementById("seats").innerHTML += selectedSeats.value;
            }
            document.getElementById("seats").innerHTML = "좌석: " + selectedSeats.value.substring(0, selectedSeats.value.length-1);
        }
    </script>
</head>
<body>
<header>
    <div class="logo">TOPFLIX</div>
    <nav>
        <ul>
            <li><a href="#">HOME</a></li>
            <li><a href="#">MY PAGE</a></li>
        </ul>
        <button class="login-btn">로그인</button>
    </nav>
</header>
<main>
    <section class="seat-selection">
        <div class="ui">
            <div class="screen">SCREEN</div>
            <div class="seats">
                <form action="payment.jsp" method="post">
                    <input type="hidden" id="selectedSeats" name="selectedSeats" value="">
                    <c:forEach var="row" items="${seats}" varStatus="rowStatus">
                        <div class="seat-row">
                            <span class="row-label">${rowLabels[rowStatus.index]}</span>
                            <c:forEach var="seat" items="${row}">
                                <span id="${rowLabels[rowStatus.index]}${seat}" class="seat available" onclick="selectSeat('${rowLabels[rowStatus.index]}', '${seat}')">${seat}</span>
                            </c:forEach>
                        </div>
                    </c:forEach>
                </form>
            </div>
        </div>
        <div class="movie-info">
            <h2>${movie.movieTitle}</h2>
            <h4>${movie.movieTitleEng}</h4>
            <p>극장: 영화관이름</p>
            <p>상영관: 1관 5층</p>
            <p>남은 좌석: 170/170</p>
            <p>2024.07.13(토) 13:10 ~ 14:56</p>
            <div class="seat-status">
                <span class="selected">
                    <div style='background-color: rgb(0, 0, 255)'></div>
                    선택
                </span>
                <span class="booked">예매완료</span>
                <span class="available">예매가능</span>
            </div>
        </div>
    </section>
    <section class="booking-summary">
        <div class="movie-poster">
            <img src="${movie.movieImage}" alt="영화 포스터">
        </div>
        <div class="summary-info">
            <h3>${movie.movieTitle}</h3>
            <p>${movie.movieRating}</p>
            <p>2024.07.13(토) 13:10</p>
            <p>상영관: 1관 5층</p>
            <p id="seats">좌석: </p>
        </div>
        <button type="submit" class="confirm-btn">결제하기</button>
    </section>
</main>
<footer>
    <div class="footer-content">
        <ul>
            <li><a href="#">회사소개</a></li>
            <li><a href="#">이용약관</a></li>
            <li><a href="#">개인정보처리방침</a></li>
            <li><a href="#">고객센터</a></li>
        </ul>
        <p>© 2024 TOPFLIX. All rights reserved.</p>
    </div>
</footer>
</body>
</html>
