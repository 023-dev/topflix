package com.topflix.action;

import com.topflix.repository.MovieRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class ChartAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MovieRepository movieRepository = new MovieRepository();
        request.setAttribute("movies", movieRepository.findAll());
        return "chart.jsp";
    }
}
