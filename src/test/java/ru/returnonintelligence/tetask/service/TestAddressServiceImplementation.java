package ru.returnonintelligence.tetask.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.returnonintelligence.testask.entities.Address;
import ru.returnonintelligence.testask.service.ServiceException;
import ru.returnonintelligence.testask.service.ServiceFactory;
import ru.returnonintelligence.testask.service.interfaces.AddressService;

/**
 * Created by Anton on 29.03.2015.
 */
public class TestAddressServiceImplementation {

    public ServiceFactory sf;
    public AddressService as;


    @Before
    public void setUp() {
        sf = new ServiceFactory();
        as = sf.getAddressService();
    }
    @Test
    public void testCreateUpdateFindDeleteAddress(){

        Address addr = new Address("55555", "Country", "City", "District", "Street");
        try {
            addr = as.create(addr);
        } catch (ServiceException ex){
            ex.printStackTrace();
        }
        Assert.assertNotNull("Address not created", addr);

        // find address
        try {
            addr = as.find(addr);
        } catch (ServiceException ex) {
            ex.printStackTrace();
        }

        Assert.assertNotNull("Address not found", addr);

        String newCountry = "New Contry";
        addr.setCountry(newCountry);
        try {
            as.update(addr);
        } catch(ServiceException ex) {
            ex.printStackTrace();
        }
        try {
            addr = as.find(addr);
        } catch (ServiceException ex) {
            ex.printStackTrace();
        }
        Assert.assertEquals("Address not updated", newCountry, addr.getCountry());

        //test delete
        Boolean delFlag = false;
        try{
            delFlag = as.delete(addr);
        } catch(ServiceException ex) {
            ex.printStackTrace();
        }
        Assert.assertTrue("Address not deleted", delFlag);
    }

    @Test(expected = ServiceException.class)
    public void invalidCreate() throws ServiceException{
        Address addr = new Address(null, "Contry", "City", "District", "Street");
        as.create(addr);
    }

    @Test
    public void testAddressValidate(){
        Address addr = new Address();
        Assert.assertFalse("Address validate not work", as.validate(addr));
        addr= new Address("000", "Contry", "City", "District", "Street");
        Assert.assertFalse("Address validate not work", as.validate(addr));
        addr = new Address("00000", "Contry", "City", "District", "Street");
        Assert.assertTrue("Address validate not work", as.validate(addr));
        addr = new Address("00000", "", "City", "District", "Street");
        Assert.assertFalse("Address validate not work", as.validate(addr));
        addr = new Address("00000", "Country", "City", "", "Street");
        Assert.assertFalse("Address validate not work", as.validate(addr));
    }


}
