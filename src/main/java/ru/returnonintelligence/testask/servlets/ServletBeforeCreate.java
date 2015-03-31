package ru.returnonintelligence.testask.servlets;

import ru.returnonintelligence.testask.entities.Group;
import ru.returnonintelligence.testask.service.ServiceFactory;
import ru.returnonintelligence.testask.service.interfaces.GroupService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Ant on 31.03.2015.
 * Add information about groups to attributes
 */
@WebServlet("/forwardToCreate")
public class ServletBeforeCreate extends HttpServlet{

    String forwardPage = "views/createUser.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("test");
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("test");
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp)  throws IOException{

        ServiceFactory sf = new ServiceFactory();
        GroupService gs = sf.getGroupService();

        List<Group> groups = gs.getAll();

        for(Group gr: groups) {
            System.out.println(gr);
        }
        req.getSession().setAttribute("groups", groups);
        req.getSession().setAttribute("test", "test");
        resp.sendRedirect(forwardPage);

    }
}
