package com.topflix.repository;

import com.topflix.db.ConnectionProvider;
import com.topflix.domain.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PaymentRepository {

    public int insertPayment(Payment p) {
        int re = -1;
        String sql = "INSERT INTO `PAYMENT` VALUES (?, ?, ?, ?, ?, ?,current_date,current_date)";

        try {
            Connection conn = ConnectionProvider.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, p.getPgToken());
            pstmt.setString(2, p.getDate());
            pstmt.setString(3, p.getPaymentType());
            pstmt.setInt(4, p.getAmount());
            pstmt.setString(5, p.getUserEmail());
            pstmt.setString(6, p.getMovieTitle());
            re = pstmt.executeUpdate();
            ConnectionProvider.close(pstmt, conn);
        } catch (Exception e) {
            System.out.println("예외발생:" + e.getMessage());
        }
        return re;
    }
}
