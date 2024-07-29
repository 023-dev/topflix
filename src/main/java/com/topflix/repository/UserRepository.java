package com.topflix.repository;

import com.topflix.db.ConnectionProvider;
import com.topflix.domain.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRepository {
    public int isMember(String email, String password) {
        int re = -1;
        String sql = "select user_pwd from user where user_email=?";
        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String hashedPassword = rs.getString("user_pwd");
                Argon2 argon2 = Argon2Factory.create();
                if (argon2.verify(hashedPassword, password)) {
                    re = 1; //로그인 성공
                } else {
                    re = 0; //비밀번호 불일치
                }
            }
        } catch (Exception e) {
            System.out.println("예외발생:" + e.getMessage());
        }
        return re;
    }

    public int insert(User user) {
        int result = -1; //기본값: 실패
        String sql = "insert into user (user_email, user_pwd, user_birthday, user_name, user_phone) values(?,?,?,?,?)";
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            char[] passwordCharArray = user.getPassword().toCharArray();
            Argon2 argon2 = Argon2Factory.create();
            String hashedPassword = argon2.hash(10, 65536, 1, passwordCharArray);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, hashedPassword);
            preparedStatement.setString(3, String.valueOf(user.getBirthday()));
            preparedStatement.setString(4, user.getName());
            preparedStatement.setString(5, user.getPhone());
            result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("예외발생:" + e.getMessage());
        }
        return result;
    }

    public int update(User user) {
        int result = -1; //기본값: 실패
        String sql = "update user set user_pwd = ?, user_phone = ? where user_email = ?";
        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            char[] passwordCharArray = user.getPassword().toCharArray();
            Argon2 argon2 = Argon2Factory.create();
            String hashedPassword = argon2.hash(10, 65536, 1, passwordCharArray);
            preparedStatement.setString(1, hashedPassword);
            preparedStatement.setString(2, user.getPhone());
            preparedStatement.setString(3, user.getEmail());
            result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("예외발생:" + e.getMessage());
        }
        return result;
    }


    public int emailCheck(String email) {
        int re = 1; // 기본값: 이메일 없음
        String sql = "SELECT COUNT(*) FROM user WHERE user_email = ?";
        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    re = 0; // 이메일 존재
                }
            }
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
        return re;
    }

    public int isEmailDuplicate(String email) {
        int result = 0; //기본값 이메일 중복안됨
        String sql = "select count(*) from user where user_email=?";
        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    if (count > 0) {
                        result = 1; //이메일 중복됨
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
        return result;
    }


    //email로 원하는 유저 정보 조회 (비밀번호 빼고 다)
    public User getUserByEmail(String email) {
        String sql = "SELECT user_name, user_phone, user_birthday FROM user WHERE user_email = ?";
        User user = null;
        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {

                    String name = resultSet.getString("user_name");
                    String phone = resultSet.getString("user_phone");
                    Date birthdate = resultSet.getDate("user_birthday");

                    user = new User();
                    user.setEmail(email);
                    user.setName(name);
                    user.setPhone(phone);
                    user.setBirthday(birthdate);
                }
            }
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
        return user;
    }


    public int withdraw(String email) {
        int result = -1; //기본값 실패
        String sql = "delete from user where user_email = ?";
        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            result = preparedStatement.executeUpdate(); //삭제된 레코드 수
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
        return result;
    }


}
