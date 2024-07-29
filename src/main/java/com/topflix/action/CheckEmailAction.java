package com.topflix.action;

import com.topflix.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CheckEmailAction implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserRepository userRepository = new UserRepository();
        String email = request.getParameter("email");
        int isDuplicate = userRepository.isEmailDuplicate(email);
        PrintWriter out = response.getWriter();
        String jsonResponse;
        if (isDuplicate == 0) {
            jsonResponse = "{\"status\":\"success\", \"message\":\"사용 가능한 이메일입니다.\"}";
        } else {
            jsonResponse = "{\"status\":\"error\", \"message\":\"이미 사용 중인 이메일입니다.\"}";
        }
        out.print(jsonResponse);
        out.flush();

        return null;

    }
}
