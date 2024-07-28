package com.topflix.repository;

import com.topflix.db.ConnectionProvider;
import com.topflix.domain.Wish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WishRepository {

    public void deleteWish(int wishSeq) {
        String query = "DELETE FROM WISH WHERE WISH_SEQ = ?";
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, wishSeq);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveWish(Wish wish) {
        String query = "INSERT INTO WISH (USER_EMAIL, MOVIE_TITLE) VALUES (?, ?)";
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, wish.getUserEmail());
            preparedStatement.setString(2, wish.getMovieTitle());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Wish> findAllByEmail(String email) {
        List<Wish> wishList = new ArrayList<>();
        String query = "SELECT * FROM WISH WHERE USER_EMAIL = ?";

        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Wish wish = new Wish();
                    wish.setWishSeq(resultSet.getInt("WISH_SEQ"));
                    wish.setUserEmail(resultSet.getString("USER_EMAIL"));
                    wish.setMovieTitle(resultSet.getString("MOVIE_TITLE"));
                    wishList.add(wish);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wishList;
    }
}