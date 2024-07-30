package com.topflix.action;

import com.topflix.domain.Payment;
import com.topflix.repository.PaymentRepository;

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

        PaymentRepository paymentRepository  = new PaymentRepository();
        Payment payment = new Payment(pg_token,
                "2024-07-17",
                "kakaoPay",
                totalAmount,
                "test@test.com",
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

        return "/views/success.jsp";
    }
}