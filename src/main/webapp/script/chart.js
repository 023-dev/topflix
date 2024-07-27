function openTab(tabName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablink");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active-title", "");
    }
    document.getElementById(tabName).style.display = "block";
    document.getElementById(tabName + "Btn").className += " active-title";
}

window.onload = function () {
    document.getElementById("movieChartBtn").onclick = function () {
        openTab('movie-chart');
    }

    document.getElementById("upcomingMoviesBtn").onclick = function () {
        openTab('upcoming-movies');
    }

    // 초기 탭 설정
    openTab('movie-chart');
}
