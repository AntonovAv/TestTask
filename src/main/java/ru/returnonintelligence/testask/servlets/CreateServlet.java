package ru.returnonintelligence.testask.servlets;

import ru.returnonintelligence.testask.entities.Address;
import ru.returnonintelligence.testask.entities.Group;
import ru.returnonintelligence.testask.entities.User;
import ru.returnonintelligence.testask.service.ServiceException;
import ru.returnonintelligence.testask.service.ServiceFactory;
import ru.returnonintelligence.testask.service.interfaces.AddressService;
import ru.returnonintelligence.testask.service.interfaces.GroupService;
import ru.returnonintelligence.testask.service.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.sql.Date;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Created by Ant on 31.03.2015.
 */
@WebServlet("/createServlet")
public class CreateServlet extends HttpServlet {
    String redirectPage = "/index.jsp";
    String redirectIfError = "/forwardToCreate";
    String errAttr = "err";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req,resp);
    }

    private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        ServiceFactory sf = new ServiceFactory();
        GroupService gs = sf.getGroupService();
        UserService us = sf.getUserService();
        AddressService as = sf.getAddressService();

        User user = new User();
        user.setFirstName(req.getParameter("fName"));
        user.setLastName(req.getParameter("lName"));
        user.setUserName(req.getParameter("uName"));
        user.setUserPassword(req.getParameter("uPassword"));
        user.setEmail(req.getParameter("uEmail"));
        String bidthday = req.getParameter("uBirthday");
        int day = 1;
        int month = 1;
        int year = 1990;
        Scanner sc = new Scanner(bidthday);
        if (sc.hasNextInt()) {
            day = sc.nextInt();
        }
        if (sc.hasNextInt()) {
            month = sc.nextInt();
        }
        if (sc.hasNextInt()) {
            year = sc.nextInt();
        }

        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day);
        user.setBirthday(new Date(cal.getTimeInMillis()));

        System.out.println(user);
        Address addr = new Address();
        addr.setZip(req.getParameter("aZip"));
        addr.setCountry(req.getParameter("aCountry"));
        addr.setCity(req.getParameter("aCity"));
        addr.setDistrict(req.getParameter("aDistrict"));
        addr.setStreet(req.getParameter("aStreet"));
        System.out.println(addr);

        Long idGroup = Long.parseLong(req.getParameter("uGroup"));
        Group group = new Group();
        group.setId(idGroup);
        System.out.println(group);


        try {
            group = gs.find(group);
        } catch (ServiceException e) {
            e.printStackTrace();
            req.getSession().setAttribute(errAttr, "Invalid group data");
            resp.sendRedirect(redirectIfError);
            return;
        }

        try {
            addr = as.create(addr);
        } catch (ServiceException e) {
            e.printStackTrace();
            req.getSession().setAttribute(errAttr, "Invalid address data");
            resp.sendRedirect(redirectIfError);
            return;
        }

        user.setAddress(addr);
        user.setGroup(group);

        try {
            user = us.create(user);
        } catch (ServiceException e) {
            e.printStackTrace();
            req.getSession().setAttribute(errAttr, "Invalid user data");
            resp.sendRedirect(redirectIfError);
            return;
        }

        resp.sendRedirect(redirectPage);

    }
}
