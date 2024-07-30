package com.topflix.repository;

import com.topflix.db.ConnectionProvider;
import com.topflix.domain.Reservation;
import com.topflix.domain.Wish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepository {
    public void saveReservation(Reservation reservation) {
        String query = "INSERT IGNORE INTO RESERVATION (RESERVATION_ID, RESERVATION_DATE, RESERVATION_THEATER, USER_EMAIL, MOVIE_TITLE, PAYMENT_CODE) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, reservation.getReservationId());
            preparedStatement.setString(2, reservation.getReservationDate());
            preparedStatement.setString(3, reservation.getReservationTheater());
            preparedStatement.setString(4, reservation.getUserEmail());
            preparedStatement.setString(5, reservation.getMovieTitle());
            preparedStatement.setString(6, reservation.getPaymentCode());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateReservation(Reservation reservation) {}
    public void deleteReservation(Reservation reservation) {}
    public List<Reservation> findByEmail(String email) {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT RESERVATION_ID, RESERVATION_DATE, RESERVATION_THEATER, USER_EMAIL, MOVIE_TITLE, PAYMENT_CODE FROM RESERVATION WHERE USER_EMAIL = ?";

        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Reservation reservation = new Reservation();
                    reservation.setReservationId(resultSet.getString("RESERVATION_ID"));
                    reservation.setReservationDate(resultSet.getString("RESERVATION_DATE"));
                    reservation.setReservationTheater(resultSet.getString("RESERVATION_THEATER"));
                    reservation.setUserEmail(resultSet.getString("USER_EMAIL"));
                    reservation.setMovieTitle(resultSet.getString("MOVIE_TITLE"));
                    reservation.setPaymentCode(resultSet.getString("PAYMENT_CODE"));
                    reservations.add(reservation);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }
}
