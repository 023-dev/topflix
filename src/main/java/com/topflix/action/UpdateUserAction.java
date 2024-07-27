package com.topflix.action;

import com.topflix.domain.User;
import com.topflix.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateUserAction implements Action{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userEmail = "test@test.com";

//        HttpSession session = request.getSession();
//        String email = (String) session.getAttribute("userEmail");

        UserRepository userRepository = new UserRepository();
        User user = userRepository.getUserByEmail(userEmail);

        System.out.println(user.getPhone());

        request.setAttribute("user", user);
//        String a = (String) request.getAttribute("userEmail");
//        request.setAttribute("userEmail", user.getEmail());

        return "/views/updateUser.jsp";
    }
}
