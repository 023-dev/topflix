<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>좌석 선택 - 영화제목</title>
    <link rel="stylesheet" href="../css/ticket.css">
    <script src="../script/ticket.js"></script>
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
            const seatsText = selectedSeats.value.substring(0, selectedSeats.value.length-1);
            document.getElementById("seats").innerHTML = "좌석: " + seatsText;
            console.log(seatsText);
            document.getElementById("inputSeats").value = seatsText;
            document.getElementById("quantity").innerText = "인원: "+seatsText.split(",").length+"명";
        }
    </script>
</head>
<body>
<header>
    <%@ include file="../includes/header.jsp" %>
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
            <p>극장: 영화관이름</p>
            <p>상영관: 1관 5층</p>
            <p>남은 좌석: 170/170</p>
            <p>2024.07.13(토) 13:10 ~ 14:56</p>
            <div class="seat-status">
                <span class="selected">
                   <div class="seat-box selected-box"></div>
                    선택
                </span>
                <span class="booked">
                    <div class="seat-box booked-box"></div>
                    예매가능
                </span>
                <span class="available">
                    <div class="seat-box available-box">
                        <span class="cross">✕</span>
                    </div>
                    예매완료
                </span>
            </div>
            <div class="genre-tabs">
                <span class="genre-tab"># 장르1</span>
                <span class="genre-tab"># 장르2</span>
                <span class="genre-tab"># 장르3</span>
            </div>
        </div>
    </section>
    <section class="booking-summary">
        <div class="movie-poster">
            <img src="${movie.movieImage}" alt="영화 포스터">
        </div>
        <div class="summary-info">
            <h3>영화제목</h3>
            <p>전체 관람가</p>
            <p>상영시간</p>
            <p>극장: 중간 영화관</p>
            <p>일시: 2024.07.13(토) 13:10</p>
            <p>상영관: 1관 5층</p>
            <p id="quantity">인원: 0명</p>
            <div class="seat-details">
                <p id="seats">좌석: </p>
            </div>
        </div>
        <form id="ticketOK-form" action="ticketOK.do" method="post" accept-charset="UTF-8">
            <input type="hidden" name="movieImage" value="${movie.movieImage}">
            <input type="hidden" name="movieTitle" value="${movie.movieTitle}">
            <input type="hidden" name="theaterName" value="홍대입구">
            <input type="hidden" name="showtime" value="2024.07.13(토) 13:10">
            <input type="hidden" name="screenName" value="1관">
            <input type="hidden" name="seatPrice" value=12000>
            <input type="hidden" id="inputSeats" name="inputSeats" value="">
        </form>
        <button type="submit" form="ticketOK-form" class="confirm-btn">결제하기</button>
    </section>
</main>
<footer>
    <%@include file="../includes/footer.jsp"%>
</footer>
</body>
</html>