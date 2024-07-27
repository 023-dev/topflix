package com.topflix.controller;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.topflix.action.Action;
import com.topflix.action.KakaoPayAction;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {

    HashMap<String, Action> map;

    @Override
    public void init(ServletConfig config) throws ServletException {
        map = new HashMap<String, Action>();
        String path = config.getServletContext().getRealPath("/WEB-INF/application.properties");
        try (Reader reader = new FileReader(path)) {
            Properties prop = new Properties();
            prop.load(reader);

            for (String key : prop.stringPropertyNames()) {
                String clsName = prop.getProperty(key);
                System.out.println("대답:"+key+":"+clsName);
                Object obj = Class.forName(clsName).newInstance();
                Action action = null;
                try {
                    action = (Action) obj;
                } catch (ClassCastException e) {
                    e.printStackTrace();
                }
                map.put(key, action);
                System.out.println("Mapped " + key + " to " + clsName);
            }
        } catch (Exception e) {
            System.out.println("예외발생:" + e.getMessage());
        }
        System.out.println(KakaoPayAction.class.getClassLoader());
        System.out.println(Action.class.getClassLoader());

//        map.put("payment.do", "com.topflix.action.PaymentAction");
    }

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        String cmd = uri.substring(uri.lastIndexOf("/") + 1);

        Action action = map.get(cmd);
        if (action == null) {
            System.out.println(cmd+":"+map.get(cmd));
            throw new ServletException("No matching action found for command: " + cmd);
        }

        String viewPage = action.execute(request, response);

        if (viewPage.endsWith(".do")) {
            response.sendRedirect(viewPage);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
            dispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        execute(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        execute(request,response);
    }

}