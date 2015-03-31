package ru.returnonintelligence.tetask.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.returnonintelligence.testask.entities.Address;
import ru.returnonintelligence.testask.entities.Group;

import ru.returnonintelligence.testask.service.ServiceException;
import ru.returnonintelligence.testask.service.ServiceFactory;
import ru.returnonintelligence.testask.service.interfaces.GroupService;
import ru.returnonintelligence.testask.service.interfaces.ValidateService;

import java.util.List;


/**
 * Created by Anton on 29.03.2015.
 */

public class TestGroupServiceImplementation {

    public ServiceFactory sf;
    public GroupService gs;

    @Before
    public void setUp() {
        sf = new ServiceFactory();
        gs = sf.getGroupService();
    }

    @Test
    public void testCreateFindDeleteUpdateGroup() {
        String grName1 = "admins";
        String grName2 = "managers";
        Group gr1 = new Group(grName1);
        Group gr2 = new Group(grName2);

        try {
            gr1 = gs.create(gr1);
            gr2 = gs.create(gr2);
        } catch (ServiceException sx) {
            sx.printStackTrace();
        }
        Assert.assertNotNull("Group 1 no created", gr1);
        Assert.assertNotNull("Group 2 no created", gr2);
        Group foundGroup1 = null;
        Group foundGroup2 = null;
        List<Group> fGr = gs.getAll();
        for (Group g: fGr) {
            if (g.getType().equalsIgnoreCase(grName1)) {
                foundGroup1 = g;
            }
            if (g.getType().equalsIgnoreCase(grName2)) {
                foundGroup2 = g;
            }
        }

        Assert.assertNotNull("Group 1 not found", foundGroup1);
        Assert.assertNotNull("Group 2 not found", foundGroup2);

        //test update
        String newGrType1 = "adm";
        String newGrType2 = "manag";

        foundGroup1.setType(newGrType1);
        foundGroup2.setType(newGrType2);
        Boolean upFlag1 = false;
        Boolean upFlag2 = false;
        try {
            upFlag1 = gs.update(foundGroup1);
            upFlag2 = gs.update(foundGroup2);
        } catch (ServiceException ex) {
            ex.printStackTrace();
        }
        Assert.assertTrue("Group 1 not updated", upFlag1);
        Assert.assertTrue("Group 2 not updated", upFlag2);

        fGr = gs.getAll();
        for (Group g: fGr) {
            if (g.getType().equalsIgnoreCase(newGrType1)) {
                foundGroup1 = g;
            }
            if (g.getType().equalsIgnoreCase(newGrType2)) {
                foundGroup2 = g;
            }
        }
        Assert.assertNotNull("Group 1 not found after update", foundGroup1);
        Assert.assertNotNull("Group 2 not found after update", foundGroup2);

        Boolean isDelete1 = false;
        Boolean isDelete2 = false;
        try {
            isDelete1 = gs.delete(foundGroup1);
            isDelete2 = gs.delete(foundGroup2);
        } catch (ServiceException sx) {
            sx.printStackTrace();
        }
        Assert.assertTrue("Group 1 not deleted" , isDelete1);
        Assert.assertTrue("Group 2 not deleted" , isDelete2);
    }

    @Test(expected = ServiceException.class)
    public void testInvalidCreate() throws ServiceException{
        Group gr = new Group();
        gs.create(gr);
    }

    @Test(expected = ServiceException.class)
    public void testInvalidFind() throws ServiceException{
        Group gr = new Group();
        gs.find(gr);
    }

    @Test
    public void testGroupValidation(){
        Group gr = new Group();
        Assert.assertFalse("Group validation not work", gs.validate(gr));
        gr = new Group("w");
        Assert.assertFalse("Group validation not work", gs.validate(gr));
        gr = new Group("admins��");
        Assert.assertFalse("Group validation not work", gs.validate(gr));
        gr = new Group("admins");
        Assert.assertTrue("Group validation not work", gs.validate(gr));
    }
}
