package com.topflix.action;

import com.topflix.domain.User;
import com.topflix.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateUserOKAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserRepository userRepository = new UserRepository();
        request.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");
        String newPassword = request.getParameter("password");
        String newPhone = request.getParameter("phone");

        User user = userRepository.getUserByEmail(email);
        HttpSession session = request.getSession();
        if (user != null) {
            // 기존 사용자 정보를 유지하면서 수정된 정보만 업데이트
            if (newPassword != null && !newPassword.trim().isEmpty()) {
                user.setPassword(newPassword);
            }
            if (newPhone != null && !newPhone.trim().isEmpty()) {
                user.setPhone(newPhone);
            }

            int updateResult = userRepository.update(user);

            // 결과에 따라 메시지 설정
            if (updateResult > 0) {
                session.setAttribute("message", "회원수정이 완료되었습니다.");
                return "myPage.do"; // 성공 시 myPage로 리다이렉션
            } else {
                session.setAttribute("message", "회원수정에 실패했습니다. 다시 시도해주세요.");
                return "updateUser.do"; // 실패 시 다시 수정 페이지로 리다이렉션
            }
        } else {
            // 사용자 정보를 찾을 수 없는 경우
            session.setAttribute("message", "사용자를 찾을 수 없습니다.");
            return "updateUser.do"; // 사용자 정보가 없는 경우 수정 페이지로 리다이렉션
        }
    }
}