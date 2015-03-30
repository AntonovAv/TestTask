package ru.returnonintelligence.testask.service;

import ru.returnonintelligence.testask.entities.Group;
import ru.returnonintelligence.testask.service.interfaces.GroupService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.validation.ValidatorFactory;
import java.util.List;

/**
 * Created by Anton on 29.03.2015.
 * Service for work with Group entity
 */
public class GroupServiceImplementation extends ValidateServiceImplementation implements GroupService {

    private EntityManagerFactory emf;

    public GroupServiceImplementation(EntityManagerFactory emf, ValidatorFactory vf) {
        super(vf);
        this.emf = emf;
    }

    @Override
    public Long create(Group group) throws ServiceException {
        if (group == null) {
            throw new ServiceException("Group parameters for create are incorrect");
        }
        if (!validate(group)) {
            throw new ServiceException("Group parameters for create are incorrect");
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Group gr = em.merge(group);
        em.getTransaction().commit();
        em.close();
        if (gr == null) {
            return -1l;
        }
        return gr.getId();
    }

    /**
     * Find group by id
     * @param group
     * @return
     * @throws ServiceException return error if group id is null
     */
    @Override
    public Group find(Group group) throws ServiceException {
        if (group == null) {
            throw new ServiceException("Group parameters for find are incorrect");
        }
        if (group.getId() == null) {
            throw new ServiceException("Group id for find not found");
        }
        EntityManager em = emf.createEntityManager();
        Group groupF = em.find(Group.class, group.getId());
        em.close();
        return groupF;
    }

    /**
     * Find all groups
     * @return
     *
     */
    @Override
    public List<Group> getAll(){
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Group.getAllGroups", Group.class);
        return query.getResultList();
    }

    /**
     * Delete group if contains id
     * @param group
     * @return
     * @throws ServiceException if group not contains id
     */
    @Override
    public Boolean delete(Group group) throws ServiceException {
        Group delGroup;
        if (group == null || group.getId() == null) {
            throw new ServiceException("Group for delete not found");
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        delGroup = em.find(Group.class, group.getId());

        em.remove(delGroup);
        em.getTransaction().commit();
        em.close();
        return true;
    }

    @Override
    public Boolean update(Group group) throws ServiceException {
        if (group == null || group.getId() == null) {
            throw new ServiceException("Group not updated");
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Group updGroup = em.merge(group);
        em.getTransaction().commit();
        em.close();
        if(updGroup == null) {
            return false;
        }
        return true;
    }
}
