package com.topflix.action;

import com.topflix.domain.User;
import com.topflix.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateUserOKAction implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserRepository userRepository = new UserRepository();
        request.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");
        String newPassword = request.getParameter("password");
        String newPhone = request.getParameter("phone");

        User user = userRepository.getUserByEmail(email);
        if(user != null) {
            // 기존 사용자 정보를 유지하면서 수정된 정보만 업데이트
            if (newPassword != null && !newPassword.trim().isEmpty()) {
                user.setPassword(newPassword);
            }
            if (newPhone != null && !newPhone.trim().isEmpty()) {
                user.setPhone(newPhone);
            }

            int updateResult = userRepository.update(user);

            HttpSession session = request.getSession();
            session.getAttribute("userSession");

            // 결과에 따라 메시지 설정
            if (updateResult > 0) {
                request.setAttribute("message", "회원수정이 완료되었습니다.");
            } else {
                request.setAttribute("message", "회원수정에 실패했습니다. 다시 시도해주세요.");
            }
        } else {
            // 사용자 정보를 찾을 수 없는 경우
            request.setAttribute("message", "사용자를 찾을 수 없습니다.");
        }

        return "/myPage.jsp";
    }
}