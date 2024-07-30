package com.topflix.action;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.topflix.domain.Payment;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class KakaoPayAction implements Action {
    private static final String KAKAOPAY_API_URL = "https://kapi.kakao.com/v1/payment/ready";
    private static final String KAKAOPAY_ADMIN_KEY = "9a6ae0ffeda654af12a486827c4dc6d9";

    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        String movieTitle = (String) session.getAttribute("movieTitle");
        int quantity = ((String)session.getAttribute("inputSeats")).split(",").length;
        int seatPrice = Integer.parseInt((String)session.getAttribute("seatPrice"));
        int totalAmount = seatPrice * quantity;

        Payment payment = new Payment();
        payment.setMovieTitle(movieTitle);
        payment.setAmount(totalAmount);

        String redirectUrl = initKakaoPay(payment, quantity);
        return redirectUrl;
    }

    private String initKakaoPay(Payment payment, Integer quantity) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(KAKAOPAY_API_URL);
        httpPost.addHeader("Authorization", "KakaoAK " + KAKAOPAY_ADMIN_KEY);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        String requestBody = "cid=TC0ONETIME"
                + "&partner_order_id=partner_order_id"
                + "&partner_user_id=partner_user_id"
                + "&item_name=" + payment.getMovieTitle()
                + "&quantity=" + quantity
                + "&total_amount=" + payment.getAmount()
                + "&vat_amount=200"
                + "&tax_free_amount=0"
                + "&approval_url=http://localhost:8080/kakaopaySuccess.do"
                + "&fail_url=http://localhost:8080/kakaopayFail.do"
                + "&cancel_url=http://localhost:8080/kakaopayCancel.do";

        httpPost.setEntity(new StringEntity(requestBody, "UTF-8"));


        try (CloseableHttpResponse httpResponse = httpClient.execute(httpPost)) {
            String responseString = EntityUtils.toString(httpResponse.getEntity());
            JsonObject jsonResponse = JsonParser.parseString(responseString).getAsJsonObject();
            return jsonResponse.get("next_redirect_pc_url").getAsString();
        }
    }
}