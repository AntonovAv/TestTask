package ru.returnonintelligence.tetask.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.returnonintelligence.testask.entities.Address;
import ru.returnonintelligence.testask.entities.Group;
import ru.returnonintelligence.testask.entities.User;
import ru.returnonintelligence.testask.service.ServiceException;
import ru.returnonintelligence.testask.service.ServiceFactory;
import ru.returnonintelligence.testask.service.interfaces.AddressService;
import ru.returnonintelligence.testask.service.interfaces.GroupService;
import ru.returnonintelligence.testask.service.interfaces.UserService;
import ru.returnonintelligence.testask.service.interfaces.ValidateService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Anton on 29.03.2015.
 */
public class TestUserServiceImplementation {

    public ServiceFactory sf;
    public GroupService gs;
    public AddressService as;
    public UserService us;
    Address address1;
    Address address2;
    Address address3;

    Group group;
    String groupType = "admin";

    Calendar calendar;
    Date birthDate1;
    Date birthDate2;
    Date birthDate3;
    String user1FName = "usOne";
    String user2FName = "usTwo";
    String user3FName = "usThree";
    String user1LName = "userovOne";
    String user2LName = "userovTwo";
    String user3LName = "userovThree";
    String user1UserName = "user1";
    String user2UserName = "user2";
    String user3UserName = "user3";
    String user1Email = "email1@email.com";
    String user2Email = "email2@email.com";
    String user3Email = "email3@email.com";
    String user1Password = "1111";
    String user2Password = "2222";
    String user3Password = "3333";


    @Before
    public void setUp(){
        sf = new ServiceFactory();
        gs = sf.getGroupService();
        as = sf.getAddressService();
        us = sf.getUserService();

        calendar = Calendar.getInstance();
        calendar.set(1992, Calendar.JUNE, 05);
        birthDate1 = new Date(calendar.getTimeInMillis());
        calendar.set(1994, Calendar.JULY, 20);
        birthDate2 = new Date(calendar.getTimeInMillis());
        birthDate3 = birthDate2;
        group = new Group(groupType);
        address1 = new Address("00000", "Country", "City", "District", "Street");
        address2 = new Address("11111", "Country", "City", "District", "Street");
        address3 = new Address("33333", "Country", "City", "District", "Street");

        try {
            group = gs.create(group);
        } catch (ServiceException ex) {
            ex.printStackTrace();
        }
        Assert.assertNotNull("Group for test not created", group);

    }

    @After
    public void close() {
        // Delete all users with group
        try {
            Assert.assertTrue("Test group not deleted", gs.delete(group));
        } catch (ServiceException sx) {
            sx.printStackTrace();
        }
    }

    @Test
    public void testComplexUser(){
        try {
            address1 = as.create(address1);
            address2 = as.create(address2);
        } catch (ServiceException se) {
            se.printStackTrace();
        }
        Assert.assertNotNull("Addr 1 for test not created", address1);
        Assert.assertNotNull("Addr 2 for test not created", address2);


        User user1 = new User(user1FName, user1LName, user1UserName, user1Password, user1Email, birthDate1, group, address1);
        User user2 = new User(user2FName, user2LName, user2UserName, user2Password,user2Email, birthDate2, group, address2);
        try {
            user1 = us.create(user1);
            user2 = us.create(user2);
        } catch (ServiceException se) {
            se.printStackTrace();
        }
        Assert.assertNotNull("User1 not created", user1);
        Assert.assertNotNull("User2 not created", user2);

        user1 = new User(user1FName, user1LName, user1UserName, user1Password, user1Email, birthDate1, group, address1);
        user2 = new User(user2FName, user2LName, user2UserName, user2Password,user2Email, birthDate2, group, address2);
        // find by data
        try {
            user1 = us.findByPersonalData(user1);
            user2 = us.findByPersonalData(user2);
        } catch (ServiceException sx) {
            sx.printStackTrace();
        }
            Assert.assertNotNull("User 1 not found", user1);
            Assert.assertNotNull("User 2 not found", user2);

        // get users by group
        Group tGr = null;
        List<Group> groups = gs.getAll();
        for (Group g: groups) {
             if (g.getType().equalsIgnoreCase(groupType)) {
                 tGr = g;
             }
        }
        Assert.assertNotNull("Group for test not founded", tGr);
        List<User> users = new ArrayList<>();
        try {
            users = us.getByGroup(tGr);
        } catch (ServiceException sx) {
            sx.printStackTrace();
        }

        Assert.assertEquals("Users from group not working", 2, users.size());

            // get All users
        users = us.getAll();
        Assert.assertEquals("Users get all not working", 2, users.size());

        try {
            for (User user : users) {
                Assert.assertTrue(user + " not deleted", us.delete(user));
            }
        } catch (ServiceException sx) {
            sx.printStackTrace();
        }
        // test deleted addresses
        List<Address> adresses = as.getAll();
        Assert.assertEquals("Addresses not deleted with user", 0, adresses.size());
    }


    @Test(expected = ServiceException.class)
    public void testUserUpdateWhenActiveAndPassive() throws ServiceException {
        try {
            address3 = as.create(address3);
        } catch (ServiceException se) {
            se.printStackTrace();
        }
        Assert.assertNotNull("Addr 3 for test not created", address3);
        User user3 = new User(user3FName, user3LName, user3UserName, user3Password, user3Email, birthDate3, group, address3);
        try {
            user3 =  us.create(user3);
        } catch (ServiceException se) {
            se.printStackTrace();
        }
        Assert.assertNotNull("Test user not created", user3);

        Boolean activeFlag = false;
        try {
            activeFlag = us.activate(user3);
        } catch (ServiceException se) {
            se.printStackTrace();
        }
        Assert.assertTrue("User not activated", activeFlag);

        try {
            user3 = us.findByPersonalData(user3);
        } catch (ServiceException se) {
            se.printStackTrace();
        }
        Assert.assertNotNull("Test user not found", user3);
        // test update when active
        String newUserName = "Anton";
        user3.setFirstName(newUserName);

        Boolean updFlag = false;
        try {
            updFlag = us.update(user3);
        } catch (ServiceException se) {
            se.printStackTrace();
        }
        Assert.assertTrue("User not updated", updFlag);

        try {
            user3 = us.findByPersonalData(user3);
        } catch (ServiceException se) {
            se.printStackTrace();
        }
        Assert.assertNotNull("Test user not found", user3);
        // test new name after update
        Assert.assertEquals("User fName not updated after activate!", newUserName.toLowerCase(), user3.getFirstName().toLowerCase());


        Boolean passFlag = false;
        try {
            passFlag = us.passivate(user3);
        } catch (ServiceException se) {
            se.printStackTrace();
        }
        Assert.assertTrue("User not passivated", passFlag);

        try {
            user3 = us.findByPersonalData(user3);
        } catch (ServiceException se) {
            se.printStackTrace();
        }
        Assert.assertNotNull("Test user not found", user3);
        // change field in user
        user3.setLastName("Antonov");
        // update (should return an error)
        us.update(user3);

    }

    @Test
    public void testUserValidation(){
        Group group = new Group("admins");
        Address address = new Address("00000", "Country", "City", "District", "Street");
        User user = new User();
        Assert.assertFalse("User validation not work", us.validate(user));
        user = new User("Fname", "Lastname", "userName", "password", "email@email.com", new Date(System.currentTimeMillis()), group, address );
        Assert.assertTrue("User validate not work", us.validate(user));
        user.setEmail("dsds");
        Assert.assertFalse("User validation email not work", us.validate(user));
        user.setEmail("email@email.ru");
        user.setGroup(null);
        Assert.assertFalse("User validation group not work", us.validate(user));
        user.setGroup(group);
        user.setAddress(null);
        Assert.assertFalse("User validation address not work", us.validate(user));
        user.setAddress(address);
        user.setFirstName("a");
        Assert.assertFalse("User validation fName not work", us.validate(user));
    }
}
