package ru.returnonintelligence.testask.service.interfaces;


import ru.returnonintelligence.testask.entities.Address;
import ru.returnonintelligence.testask.service.ServiceException;

import java.util.List;

/**
 * Created by Anton on 29.03.2015.
 */
public interface AddressService extends ValidateService {

    public Long create(Address address) throws ServiceException;
    public List<Address> getAll();
    public Boolean delete(Address address) throws ServiceException;
    public Boolean update(Address address) throws ServiceException;
    public Address find(Address address) throws ServiceException;
}
