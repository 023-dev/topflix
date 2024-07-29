package com.topflix.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class TicketOKAction implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        session.setAttribute("movieImage", request.getParameter("movieImage"));
        session.setAttribute("movieTitle", request.getParameter("movieTitle"));
        session.setAttribute("theaterName", request.getParameter("theaterName"));
        session.setAttribute("showtime", request.getParameter("showtime"));
        session.setAttribute("screenName", request.getParameter("screenName"));
        session.setAttribute("seatPrice", request.getParameter("seatPrice"));
        session.setAttribute("inputSeats", request.getParameter("inputSeats"));

        return "kakaopay.do";
    }
}
