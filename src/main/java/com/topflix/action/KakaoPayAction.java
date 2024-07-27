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

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/kakaopay.do")
public class KakaoPayAction extends HttpServlet {
    private static final String KAKAOPAY_API_URL = "https://kapi.kakao.com/v1/payment/ready";
    private static final String KAKAOPAY_ADMIN_KEY = "9a6ae0ffeda654af12a486827c4dc6d9";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        String movieTitle = request.getParameter("movieTitle");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int totalAmount = Integer.parseInt(request.getParameter("price")) * quantity;

        Payment payment = new Payment();
        payment.setMovieTitle(movieTitle);
//        payment.set(quantity); 몇개인지
        payment.setAmount(totalAmount);

        HttpSession session = request.getSession();
        session.setAttribute("movieTitle", movieTitle);
        session.setAttribute("quantity", quantity);
        session.setAttribute("totalAmount", totalAmount);

        String redirectUrl = initiateKakaoPay(payment, quantity);
        response.sendRedirect(redirectUrl);
    }

    private String initiateKakaoPay(Payment payment, Integer quantity) throws IOException {
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

        httpPost.setEntity(new StringEntity(requestBody));

        try (CloseableHttpResponse httpResponse = httpClient.execute(httpPost)) {
            String responseString = EntityUtils.toString(httpResponse.getEntity());
            JsonObject jsonResponse = JsonParser.parseString(responseString).getAsJsonObject();
            return jsonResponse.get("next_redirect_pc_url").getAsString();
        }
    }
}