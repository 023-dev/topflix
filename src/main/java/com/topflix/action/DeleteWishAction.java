package com.topflix.action;

import com.topflix.repository.WishRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteWishAction implements Action{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String movieTitle = request.getParameter("title");
        WishRepository wishRepository = new WishRepository();
        int result = wishRepository.deleteWish(movieTitle);
        if (result == 1) {
            return "wish.do";
        } else {
            return "error";
        }
    }
}