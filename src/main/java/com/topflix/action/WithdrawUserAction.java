package com.topflix.action;

import com.topflix.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class WithdrawUserAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserRepository userRepository = new UserRepository();
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("userEmail");
        String password = request.getParameter("password");

        if (email == null) { //  email.isEmpty 로 변경 예정
            request.setAttribute("message", "로그인 세션이 만료되었습니다.");
            return "signIn.do";
        }

        int isMember = userRepository.isMember(email, password);
        System.out.println(isMember);
        if (isMember == 1) { // 비번일치 시
            int result = userRepository.withdraw(email);
            if (result == 1) {// 탈퇴 성공
                request.setAttribute("message", "탈퇴 되었습니다. 안녕...");
            } else { // 탈퇴 실패
                request.setAttribute("message", "탈퇴에 실패하였습니다.");
            }
        } else {
            request.setAttribute("message", "비밀번호가 올바르지 않습니다.");
        }
        return "withdrawUser.jsp";
    }
}

