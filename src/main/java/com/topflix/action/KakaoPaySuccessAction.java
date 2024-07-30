package com.topflix.action;

import com.topflix.domain.Payment;
import com.topflix.domain.Reservation;
import com.topflix.repository.PaymentRepository;
import com.topflix.repository.ReservationRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

public class KakaoPaySuccessAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        String pg_token = request.getParameter("pg_token");
        String movieImage = (String) session.getAttribute("movieImage");
        String movieTitle = (String) session.getAttribute("movieTitle");
        String inputSeats = (String) session.getAttribute("inputSeats");
        int quantity = inputSeats.split(",").length;
        int seatPrice = Integer.parseInt((String)session.getAttribute("seatPrice"));
        int totalAmount = seatPrice * quantity;
        String theaterName = (String) session.getAttribute("theaterName");
        String showtime = (String) session.getAttribute("showtime");
        String screenName = (String) session.getAttribute("screenName");
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);
        PaymentRepository paymentRepository  = new PaymentRepository();
        Payment payment = new Payment(pg_token,
                formattedDate,
                "kakaoPay",
                totalAmount,
                (String)session.getAttribute("userEmail"),
                movieTitle);

        int re = paymentRepository.insertPayment(payment);
        if (re < 0){
            System.out.println("payment insert failed");
        }

        System.out.println("movieImage: " + movieImage);

        request.setAttribute("movieImage",movieImage);
        request.setAttribute("pg_token", pg_token);
        request.setAttribute("movieTitle", movieTitle);
        request.setAttribute("theaterName", theaterName);
        request.setAttribute("showtime", showtime);
        request.setAttribute("screenName", screenName);
        request.setAttribute("inputSeats", inputSeats);
        request.setAttribute("totalAmount", totalAmount);
        request.setAttribute("userEmail", session.getAttribute("userEmail"));

        session.removeAttribute("movieImage");
        session.removeAttribute("movieTitle");
        session.removeAttribute("theaterName");
        session.removeAttribute("showtime");
        session.removeAttribute("screenName");
        session.removeAttribute("seatPrice");
        session.removeAttribute("inputSeats");

        Reservation reservation = new Reservation();
        reservation.setReservationId(today.format(DateTimeFormatter.ofPattern("yyyyMMdd"))+pg_token);
        reservation.setReservationDate(formattedDate);
        reservation.setReservationTheater(theaterName);
        reservation.setUserEmail((String) session.getAttribute("userEmail"));
        reservation.setMovieTitle(movieTitle);
        reservation.setPaymentCode(pg_token);
        ReservationRepository reservationRepository = new ReservationRepository();
        reservationRepository.saveReservation(reservation);
        return "/views/success.jsp";
    }
}