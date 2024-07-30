package com.topflix.action;

import com.topflix.domain.Movie;
import com.topflix.domain.Wish;
import com.topflix.repository.MovieRepository;
import com.topflix.repository.WishRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WishAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userEmail = (String)session.getAttribute("userEmail");
        String movieTitle = request.getParameter("title");
        MovieRepository movieRepository = new MovieRepository();
        WishRepository wishRepository = new WishRepository();

        if (movieTitle != null) {
            wishRepository.saveWish(new Wish(0, userEmail, movieTitle));
        }

        // 저장된 영화 목록을 가져옴
        List<Wish> wishes = wishRepository.findAllByEmail(userEmail);
        List<Movie> movies = new ArrayList<Movie>();
        for (Wish wish : wishes) {
            Movie movie = movieRepository.findMovieByTitle(wish.getMovieTitle());
            movies.add(movie);
        }

        request.setAttribute("wishes", wishes);
        request.setAttribute("movies", movies);

        return "wish.jsp";
    }
}