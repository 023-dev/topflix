package com.topflix.action;

import com.topflix.repository.WishRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteWishAction implements Action{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int wishSeq = Integer.parseInt(request.getParameter("id"));

        WishRepository wishRepository = new WishRepository();
        wishRepository.deleteWish(wishSeq);
/*
        <!-- JSON -->
        <dependency>
            <groupId>org.json</groupId>
            <artifactId>json</artifactId>
            <version>20210307</version>
        </dependency>
 */

/*
        response.setContentType("application/json");
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("success", true);
        response.getWriter().write(jsonResponse.toString());
*/
        return null; // JSON 응답만 반환하므로 뷰를 반환하지 않음
    }
}