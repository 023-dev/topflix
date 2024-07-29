package com.topflix.action;

import com.topflix.domain.Movie;
import com.topflix.repository.MovieRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MainAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // MovieDAO 인스턴스 생성
        MovieRepository movieRepository = new MovieRepository();

        // findAll 메서드를 통해 영화 목록 가져오기
        List<Movie> movies = movieRepository.findAll();
        // 영화 목록을 request 객체에 설정
        request.setAttribute("movies", movies);

        // mainPage.jsp로 포워딩
        return "main.jsp";
    }
}