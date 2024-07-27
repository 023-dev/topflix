package com.topflix.action;

import com.topflix.domain.Movie;
import com.topflix.repository.MovieRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TicketAction implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MovieRepository movieRepository = new MovieRepository();
        String movieTitle = request.getParameter("title");
        Movie movie = movieRepository.findMovieByTitle(movieTitle);

        // 좌석 정보를 설정합니다. 실제로는 데이터베이스에서 가져와야 합니다.
        String[][] seats = {
                {"13", "12", "11", "10", "9", "8", "7", "6", "5", "4", "3", "2", "1"},
                {"13", "12", "11", "10", "9", "8", "7", "6", "5", "4", "3", "2", "1"},
                {"13", "12", "11", "10", "9", "8", "7", "6", "5", "4", "3", "2", "1"},
                {"13", "12", "11", "10", "9", "8", "7", "6", "5", "4", "3", "2", "1"},
                {"13", "12", "11", "10", "9", "8", "7", "6", "5", "4", "3", "2", "1"},
                {"13", "12", "11", "10", "9", "8", "7", "6", "5", "4", "3", "2", "1"},
                {"13", "12", "11", "10", "9", "8", "7", "6", "5", "4", "3", "2", "1"}
        };

        String[] rowLabels = {"A", "B", "C", "D", "E", "F", "G"};

        request.setAttribute("seats", seats);
        request.setAttribute("rowLabels", rowLabels);
        request.setAttribute("movie", movie);
        return "ticketPage.jsp";
    }
}
