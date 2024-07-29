package com.topflix.action;

import com.topflix.domain.User;
import com.topflix.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SignUpOKAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserRepository userRepository = new UserRepository();
        request.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");
        if ("emailCheck".equals(request.getParameter("action"))) {
            boolean isEmailAvailable = userRepository.emailCheck(email) == 0;
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"available\":" + isEmailAvailable + "}");
            return null; // AJAX 응답으로 완료, 더 이상의 처리가 필요 없음
        }


        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        String phone = request.getParameter("phone");
        String birthdateString = request.getParameter("birthdate");


        // 생년월일 처리
        java.sql.Date birthdate = java.sql.Date.valueOf(birthdateString);
        User user = new User(email, password, birthdate, name, phone);

        int result = userRepository.insert(user);

        if (result == 1) {
            request.setAttribute("message", "회원가입이 완료되었습니다.");
            return "signIn.do";
        } else {
            request.setAttribute("message", "회원가입에 실패했습니다. 다시 시도해주세요.");
            return "signUp.do";
        }
    }

}
