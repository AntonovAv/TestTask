package ru.returnonintelligence.testask.service.interfaces;

import ru.returnonintelligence.testask.entities.Group;
import ru.returnonintelligence.testask.service.ServiceException;

import java.util.List;

/**
 * Created by Anton on 29.03.2015.
 */
public interface GroupService extends ValidateService{

    public Long create(Group group) throws ServiceException;
    public Group find(Group group) throws ServiceException;
    public List<Group> getAll();
    public Boolean delete(Group group)throws ServiceException;
    public Boolean update(Group group) throws ServiceException;

}
