package ru.returnonintelligence.testask.servlets;

import ru.returnonintelligence.testask.entities.User;
import ru.returnonintelligence.testask.service.ServiceFactory;
import ru.returnonintelligence.testask.service.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Ant on 31.03.2015.
 */
@WebServlet("/userListServlet")
public class UserList extends HttpServlet{
    String redirectPage = "views/userList.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req,resp);
    }

    private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws IOException{

        ServiceFactory sf = new ServiceFactory();
        UserService us = sf.getUserService();

        List<User> users = us.getAll();

        req.getSession().setAttribute("users", users);

        resp.sendRedirect(redirectPage);
    }
}
