package com.topflix.repository;

import com.topflix.db.ConnectionProvider;
import com.topflix.domain.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieRepository {
    public int save(List<Movie> movies) {
        int result = -1;
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionProvider.getConnection();
            // 자동 커밋 비활성화
            connection.setAutoCommit(false);

            // 데이터 삽입 SQL 구문
            String insertSQL = "INSERT INTO MOVIE ("
                    + "MOVIE_RANK, "
                    + "MOVIE_HREF, "
                    + "MOVIE_IMAGE, "
                    + "MOVIE_RATING, "
                    + "MOVIE_TITLE, "
                    + "MOVIE_TITLE_ENG, "
                    + "MOVIE_RESERVATION_RATE, "
                    + "MOVIE_HIT_RATE, "
                    + "MOVIE_RELEASE_DATE, "
                    + "MOVIE_DDAY, "
                    + "MOVIE_DIRECTOR, "
                    + "MOVIE_RUNNING_TIME, "
                    + "MOVIE_ACTOR, "
                    + "MOVIE_GENRE, "
                    + "MOVIE_STORY, "
                    + "MOVIE_STILL_CUT) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertSQL);
            for (Movie movie : movies) {
                preparedStatement.setString(1, movie.getMovieRank());
                preparedStatement.setString(2, movie.getMovieHref());
                preparedStatement.setString(3, movie.getMovieImage());
                preparedStatement.setString(4, movie.getMovieRating());
                preparedStatement.setString(5, movie.getMovieTitle());
                preparedStatement.setString(6, movie.getMovieTitleEng());
                preparedStatement.setString(7, movie.getMovieReservationRate());
                preparedStatement.setString(8, movie.getMovieEggGage());
                preparedStatement.setString(9, movie.getMovieReleaseDate());
                preparedStatement.setString(10, movie.getMovieDday());
                preparedStatement.setString(11, movie.getMovieDirector());
                preparedStatement.setString(12, movie.getMovieRunningtime());
                preparedStatement.setString(13, movie.getMovieActor());
                preparedStatement.setString(14, movie.getMovieGenre());
                preparedStatement.setString(15, movie.getMovieStory());
                preparedStatement.setString(16, movie.getMovieStillCut());
                preparedStatement.addBatch();
            }
            statement = connection.createStatement();
            // 기존 백업 뷰 삭제
            statement.execute("DROP VIEW IF EXISTS OLD_MOVIE");
            // 기존 데이터를 백업 뷰로 저장
            statement.execute("CREATE VIEW OLD_MOVIE AS SELECT * FROM MOVIE");
            // 기존 데이터를 삭제
            statement.execute("DELETE FROM MOVIE");
            // Batch 는 여러 개의 INSERT 문을 하나의 Transaction 으로 묶어서 실행
            int[] batch = preparedStatement.executeBatch();
            // 수동 커밋
            connection.commit();
            result = batch.length;
            result = 1;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (statement != null) statement.close();
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
    public Movie findMovieByTitle(String movieTitle) {
        Movie movie = null;
        String query = "SELECT * FROM movie WHERE MOVIE_TITLE = ?";

        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, movieTitle);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    movie = new Movie();
                    movie.setMovieTitle(resultSet.getString("MOVIE_TITLE"));
                    movie.setMovieRank(resultSet.getString("MOVIE_RANK"));
                    movie.setMovieHref(resultSet.getString("MOVIE_HREF"));
                    movie.setMovieImage(resultSet.getString("MOVIE_IMAGE"));
                    movie.setMovieRating(resultSet.getString("MOVIE_RATING"));
                    movie.setMovieTitleEng(resultSet.getString("MOVIE_TITLE_ENG"));
                    movie.setMovieReservationRate(resultSet.getString("MOVIE_RESERVATION_RATE"));
                    movie.setMovieEggGage(resultSet.getString("MOVIE_HIT_RATE"));
                    movie.setMovieReleaseDate(resultSet.getString("MOVIE_RELEASE_DATE"));
                    movie.setMovieDday(resultSet.getString("MOVIE_DDAY"));
                    movie.setMovieDirector(resultSet.getString("MOVIE_DIRECTOR"));
                    movie.setMovieRunningtime(resultSet.getString("MOVIE_RUNNING_TIME"));
                    movie.setMovieActor(resultSet.getString("MOVIE_ACTOR"));
                    movie.setMovieGenre(resultSet.getString("MOVIE_GENRE"));
                    movie.setMovieStory(resultSet.getString("MOVIE_STORY"));
                    movie.setMovieStillCut(resultSet.getString("MOVIE_STILL_CUT"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movie;
    }

    public List<Movie> findAll() {
        List<Movie> movies = new ArrayList<>();
        String query = "SELECT * FROM TOPFLIX.MOVIE ORDER BY CAST(MOVIE_RANK AS UNSIGNED)";

        try (Connection connection = ConnectionProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setMovieTitle(resultSet.getString("MOVIE_TITLE"));
                movie.setMovieRank(resultSet.getString("MOVIE_RANK"));
                movie.setMovieHref(resultSet.getString("MOVIE_HREF"));
                movie.setMovieImage(resultSet.getString("MOVIE_IMAGE"));
                movie.setMovieRating(resultSet.getString("MOVIE_RATING"));
                movie.setMovieTitleEng(resultSet.getString("MOVIE_TITLE_ENG"));
                movie.setMovieReservationRate(resultSet.getString("MOVIE_RESERVATION_RATE"));
                movie.setMovieEggGage(resultSet.getString("MOVIE_HIT_RATE"));
                movie.setMovieReleaseDate(resultSet.getString("MOVIE_RELEASE_DATE"));
                movie.setMovieDday(resultSet.getString("MOVIE_DDAY"));
                movie.setMovieDirector(resultSet.getString("MOVIE_DIRECTOR"));
                movie.setMovieRunningtime(resultSet.getString("MOVIE_RUNNING_TIME"));
                movie.setMovieActor(resultSet.getString("MOVIE_ACTOR"));
                movie.setMovieGenre(resultSet.getString("MOVIE_GENRE"));
                movie.setMovieStory(resultSet.getString("MOVIE_STORY"));
                movie.setMovieStillCut(resultSet.getString("MOVIE_STILL_CUT"));
                movies.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movies;
    }
}
