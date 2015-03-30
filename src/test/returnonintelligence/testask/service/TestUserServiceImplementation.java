package returnonintelligence.testask.service;

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
import java.util.List;

/**
 * Created by Антон on 29.03.2015.
 */
public class TestUserServiceImplementation {

    public ServiceFactory sf;
    public GroupService gs;
    public AddressService as;
    public UserService us;
    Address address1;
    Address address2;
    Group group;
    String groupType = "admin";

    // TODO use correct date!!!
    Date birthDate1 = new Date(java.util.Date.parse("05/06/1992"));
    Date birthDate2 = new Date(java.util.Date.parse("20/07/1994"));
    String user1FName = "usOne";
    String user2FName = "usTwo";
    String user1LName = "userovOne";
    String user2LName = "userovTwo";
    String user1UserName = "user1";
    String user2UserName = "user2";
    String user1Email = "email1@email.com";
    String user2Email = "email2@email.com";
    String user1Password = "1111";
    String user2Password = "2222";


    @Before
    public void setUp(){
        sf = new ServiceFactory();
        gs = sf.getGroupService();
        as = sf.getAddressService();
        us = sf.getUserService();
        // TODO set correct address and group
        group = new Group(groupType);
        address1 = new Address("00000", "Country", "City", "District", "Street");
        address2 = new Address("11111", "Country", "City", "District", "Street");
        try {
            Assert.assertNotEquals("Group for test not created", -1l,(long) gs.create(group) );
            Assert.assertNotEquals("Addr 1 for test not created", -1l, (long) as.create(address1));
            Assert.assertNotEquals("Addr 2 for test not created", -1l, (long) as.create(address2));
        } catch (ServiceException ex) {
            ex.printStackTrace();
        }
    }

    @After
    public void close() {

    }

    @Test
    public void testComplexUser(){
        User user1 = new User(user1FName, user1LName, user1UserName, user1Password, user1Email, birthDate1, group, address1);
        User user2 = new User(user2FName, user2LName, user2UserName, user2Password,user2Email, birthDate2, group, address2);
        try {
            Assert.assertNotEquals("User1 not created", -1l, (long) us.create(user1));
            Assert.assertNotEquals("User2 not created", -1l, (long) us.create(user2));

            // find by data
            user1 = us.findByPersonalData(user1);
            user2 = us.findByPersonalData(user2);

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
            List<User> users = us.getByGroup(tGr);
            Assert.assertEquals("Users from group not working", 2, users.size());

            // get All users
            users = us.getAll();
            Assert.assertEquals("Users get all not working", 2, users.size());

            for (User user: users) {
                Assert.assertTrue(user + " not deleted", us.delete(user));
            }
        } catch (ServiceException ex) {
            ex.printStackTrace();
        }


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
