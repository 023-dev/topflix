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