package com.topflix.action;

import com.topflix.domain.Movie;
import com.topflix.domain.Reservation;
import com.topflix.domain.Wish;
import com.topflix.repository.MovieRepository;
import com.topflix.repository.ReservationRepository;
import com.topflix.repository.WishRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReservationAction implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userEmail = (String)session.getAttribute("userEmail");
        MovieRepository movieRepository = new MovieRepository();
        ReservationRepository reservationRepository = new ReservationRepository();

        // 저장된 영화 목록을 가져옴
        List<Reservation> reservations = reservationRepository.findByEmail(userEmail);
        List<Movie> movies = new ArrayList<Movie>();
        for (Reservation reservation : reservations) {
            Movie movie = movieRepository.findMovieByTitle(reservation.getMovieTitle());
            movies.add(movie);
        }

        request.setAttribute("reservations", reservations);
        request.setAttribute("movies", movies);
        return "reservation.jsp";
    }
}
