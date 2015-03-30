package ru.returnonintelligence.testask.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Created by Anton on 28.03.2015.
 */
@WebServlet("/test")
public class TestServlet extends HttpServlet{
/*
    UserServiceImplementation userBean = new UserServiceImplementation();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User("ant", "antonov", "olo", "1", "email", new Date(System.currentTimeMillis()),null, null);
        System.out.println(userBean.add(user));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User("ant", "antonov", "olo", "1", "email", new Date(System.currentTimeMillis()),null, null);
        user.setCreateTS(new Timestamp(System.currentTimeMillis()));
        user.setLastUpdateTs(new Timestamp(System.currentTimeMillis()));
        System.out.println(userBean.add(user));
    }
    */
}
