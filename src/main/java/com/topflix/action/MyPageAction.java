package com.topflix.action;

import com.topflix.domain.User;
import com.topflix.domain.Wish;
import com.topflix.repository.ReservationRepository;
import com.topflix.repository.UserRepository;
import com.topflix.repository.WishRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


public class MyPageAction implements Action{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        UserRepository userRepository = new UserRepository();
        String userEmail = (String) httpSession.getAttribute("userEmail");

        User user = userRepository.getUserByEmail(userEmail);
        String name = user.getName();
        String email = user.getEmail();
        String phone = user.getPhone();

        request.setAttribute("user", user);
        request.setAttribute("name", name);
        request.setAttribute("email", email);
        request.setAttribute("phone", phone);
        WishRepository wishRepository = new WishRepository();
        ReservationRepository reservationRepository = new ReservationRepository();
        request.setAttribute("reservationSize", reservationRepository.findByEmail(userEmail).size());
        request.setAttribute("wishSize", wishRepository.findAllByEmail(userEmail).size());
        return "/views/myPage.jsp";
    }
}
