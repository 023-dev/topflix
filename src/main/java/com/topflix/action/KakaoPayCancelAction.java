package com.topflix.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class KakaoPayCancelAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "/views/cancel.jsp";
    }
}
