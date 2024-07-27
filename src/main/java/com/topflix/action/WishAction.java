package com.topflix.action;

import com.topflix.domain.Movie;
import com.topflix.repository.MovieRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WishAction implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MovieRepository movieRepository = new MovieRepository();
        String movieTitle = request.getParameter("title");
        Movie movie = movieRepository.findMovieByTitle(movieTitle);
        request.setAttribute("movie", movie);
        return "wishPage.jsp";
    }
}
