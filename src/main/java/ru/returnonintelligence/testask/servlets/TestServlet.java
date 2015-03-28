package ru.returnonintelligence.testask.servlets;

import ru.returnonintelligence.testask.beans.UserBean;
import ru.returnonintelligence.testask.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Антон on 28.03.2015.
 */
@WebServlet("/test")
public class TestServlet extends HttpServlet{

    UserBean userBean = new UserBean();
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
}
