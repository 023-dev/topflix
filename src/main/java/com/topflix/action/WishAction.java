package com.topflix.action;

import com.topflix.domain.Movie;
import com.topflix.domain.Wish;
import com.topflix.repository.MovieRepository;
import com.topflix.repository.WishRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class WishAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userEmail = request.getParameter("email");
        String movieTitle = request.getParameter("title");

        MovieRepository movieRepository = new MovieRepository();
        Movie movie = movieRepository.findMovieByTitle(movieTitle);

        if (movie != null) {
            WishRepository wishRepository = new WishRepository();
            wishRepository.saveWish(new Wish(0, userEmail, movie.getMovieTitle()));

            // 저장된 영화 목록을 가져옴
            List<Wish> wishList = wishRepository.findAllByEmail(userEmail);
            request.setAttribute("wishList", wishList);
        }

        return "wish.jsp";
    }
}