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

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        String phone = request.getParameter("phone");
        String birthdateString = request.getParameter("birthdate");

        PrintWriter out2 = response.getWriter();

        // 입력 유효성 검사 -> js에서 처리

        // 이메일 중복 검사
        if (userRepository.emailCheck(email) == 0) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("message", "이미 존재하는 이메일입니다.");
            return "/views/signUp.jsp";
        }

        // 생년월일 처리
        java.sql.Date birthdate = java.sql.Date.valueOf(birthdateString);
        User user = new User(email, password, birthdate, name, phone);

        int result = userRepository.insert(user);

        if (result == 1) {
            request.setAttribute("message", "회원가입이 완료되었습니다.");
//            out2.println("<script>alert('회원가입이 완료되었습니다.'); window.location.href = 'signIn.jsp';</script>");
            return "/signIn.do";
        } else {
            request.setAttribute("message", "회원가입에 실패했습니다. 다시 시도해주세요.");
//            out2.println("<script>alert('회원가입에 실패했습니다. 다시 시도해주세요.'); window.history.back();</script>");
            return "/signUp.do";
        }
    }

}
