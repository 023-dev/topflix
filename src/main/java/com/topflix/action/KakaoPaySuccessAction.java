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
        String movieTitle = (String) session.getAttribute("movieTitle");
        int amont = (int) session.getAttribute("totalAmount");

        System.out.println(movieTitle);


        PaymentRepository paymentRepository  = new PaymentRepository();
        Payment payment = new Payment(pg_token,
                "2024-07-17",
                "kakaoPay",
                amont,
                "john.doe@example.com",
                movieTitle);

        int re = paymentRepository.insertPayment(payment);
        if (re < 0){
            System.out.println("payment insert failed");
        }

        request.setAttribute("pg_token", pg_token);
        request.setAttribute("movieTitle", movieTitle);

//        HttpSession session = request.getSession();
//        request.setAttribute("itemName", request.getParameter("itemName"));
//        request.setAttribute("quantity", request.getParameter("quantity"));
//        request.setAttribute("totalAmount", request.getParameter("totalAmount"));

        return "/views/success.jsp";
    }
}