package ru.returnonintelligence.testask.service;

import ru.returnonintelligence.testask.service.interfaces.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

/**
 * Created by Anton on 29.03.2015.
 * Create services for working with entities
 */
public class ServiceFactory {

    private static final String unitName = "jdbc_unit";
    private static final EntityManagerFactory emf;
    private static final ValidatorFactory vf;

    static {
        emf = Persistence.createEntityManagerFactory(unitName);
        vf = Validation.buildDefaultValidatorFactory();
    }

    public ServiceFactory() {}

    public UserService getUserService() {
        return new UserServiceImplementation(emf, vf);
    }

    public GroupService getGroupService() {
        return new GroupServiceImplementation(emf, vf);
    }

    public AddressService getAddressService() {
        return new AddressServiceImplementation(emf, vf);
    }
}
