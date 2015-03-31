package ru.returnonintelligence.testask.service.interfaces;

import ru.returnonintelligence.testask.entities.Group;
import ru.returnonintelligence.testask.entities.User;
import ru.returnonintelligence.testask.service.ServiceException;

import java.util.List;

/**
 * Created by Anton on 29.03.2015.
 */
public interface UserService extends ValidateService {

    public User create(User user) throws ServiceException;
    public Boolean delete(User user) throws ServiceException;
    public User find(User user) throws ServiceException;
    public User findByPersonalData(User user)throws ServiceException;
    public Boolean update(User user) throws ServiceException;
    public List<User> getAll();
    public Boolean passivate(User user) throws ServiceException;
    public Boolean activate(User user) throws ServiceException;
    public List<User> getByGroup(Group group) throws ServiceException;
}
