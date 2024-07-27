package com.topflix.action;

import com.topflix.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignInOKAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserRepository userRepository = new UserRepository();
        request.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int result = userRepository.isMember(email,password);

        if (result == 1) {
            HttpSession session = request.getSession();
            session.setAttribute("userEmail", email);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        request.setAttribute("result", result);
        return "main.do";
    }
}
