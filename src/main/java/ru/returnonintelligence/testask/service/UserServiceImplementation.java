package ru.returnonintelligence.testask.service;

import ru.returnonintelligence.testask.entities.Group;
import ru.returnonintelligence.testask.entities.User;
import ru.returnonintelligence.testask.service.interfaces.UserService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.validation.ValidatorFactory;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Anton on 28.03.2015.
 * Service for work with user entity
 */
public class UserServiceImplementation extends ValidateServiceImplementation implements UserService {

    private EntityManagerFactory emf;


    public UserServiceImplementation(EntityManagerFactory emf, ValidatorFactory vf){
        super(vf);
        this.emf = emf;
    }

    /**
     * Create user if all req fields are available
     * @param user
     * @return -1 or id created user
     */
    @Override
    public User create(User user) throws ServiceException{
        if (user == null) {
            throw new ServiceException("User parameters for create are incorrect");

        }
        if (!validate(user)) {
            throw new ServiceException("User parameters for create are incorrect");
        }
        user.setCreateTS(new Timestamp(System.currentTimeMillis())); // create TS
        user.setLastUpdateTs(new Timestamp(System.currentTimeMillis())); // last Update Ts
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User us = em.merge(user);
        em.getTransaction().commit();
        em.close();
        return us;
    }

    /**
     * found and delete user by id
     * @param user
     * @return
     * @throws ServiceException
     */
    @Override
    public Boolean delete(User user) throws ServiceException{
        User usr;
        if (user == null || user.getId() == null) {
            throw new ServiceException("User not delete because user data is incorrect");
        }

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        usr = em.find(User.class, user.getId());
        em.remove(usr);
        em.getTransaction().commit();
        em.close();
        return true;
    }

    /**
     * Find by id
     * @param user
     * @return
     * @throws ServiceException if user is null or id is null
     */
    @Override
    public User find(User user) throws ServiceException{
        if (user == null) {
            throw new ServiceException("User parameters for find are incorrect");
        }
        if (user.getId() == null) {
            throw new ServiceException("User id for find not found");
        }
        EntityManager em = emf.createEntityManager();
        User foundUs = em.find(User.class, user.getId());
        return foundUs;
    }

    /**
     * Find user by firstName, lastName, email and birthday or by id if it exist
     * @param user
     * @return
     * @throws ServiceException if parametrs for find are incorrect
     */
    @Override
    public User findByPersonalData(User user) throws ServiceException{
        if (user == null) {
            throw new ServiceException("User parameters for find are incorrect");
        }
        if (user.getId() !=null) {
            return find(user);
        }
        if (user.getFirstName() == null ||
                user.getLastName() == null ||
                user.getEmail() == null ||
                user.getBirthday() == null) {
            throw new ServiceException("User parameters for find are incorrect");
        }
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("User.findByUserData", User.class);
        query.setParameter("firstName", user.getFirstName());
        query.setParameter("lastName", user.getLastName());
        query.setParameter("email", user.getEmail());
        query.setParameter("birthday", user.getBirthday());
        User foundUs = (User) query.getSingleResult();
        return foundUs;
    }

    /**
     * Get all user from database
     * @return
     */
    @Override
    public List<User> getAll() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("User.getAllUsers", User.class);
        return query.getResultList();
    }

    /**
     * Update user if user is active or error otherwise
     * @param user for update
     * @return
     * @throws ServiceException if user is passivate
     */
    @Override
    public Boolean update(User user) throws ServiceException {
        if (user == null || user.getId() == null) {
            throw new ServiceException("User for update not exist or cannot be find");
        }

        // set time last update
        user.setLastUpdateTs(new Timestamp(System.currentTimeMillis()));
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // test user activity
        User beforeUp = em.find(User.class, user.getId());
        if (!beforeUp.getActive()) {
            em.close();
            throw new ServiceException("User not updated because user is not active!");
        }
        User updtUser = em.merge(user);
        em.getTransaction().commit();
        em.close();
        if(updtUser == null) {
            return false;
        }
        return true;
    }
    @Override
    public Boolean passivate(User user) throws ServiceException{
        if (user == null) {
            throw new ServiceException("User not passivated because user not exist");
        }
        User usr = null;
        try {
            usr = findByPersonalData(user);
        } catch (ServiceException ex) {
            throw new ServiceException("User not found and not passivated");
        }
        if (usr == null) {
            throw new ServiceException("User not found and not passivated");
        }

        usr.setActive(false);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User updtUser = em.merge(usr);
        em.getTransaction().commit();
        em.close();
        if(updtUser == null) {
            return false;
        }
        return true;
    }

    /**
     * Find user and set Fiald isActive as true
     * @param user
     * @return
     * @throws ServiceException if user is passivate
     */
    @Override
    public Boolean activate(User user) throws ServiceException{
        if (user == null) {
            throw new ServiceException("User not activated because user not found");
        }
        User usr = null;
        try {
            usr = findByPersonalData(user);
        } catch (ServiceException ex) {
            throw new ServiceException("User not found and not activated");
        }
        if (usr == null) {
            throw new ServiceException("User not found and not activated");
        }

        usr.setActive(true);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User updtUser = em.merge(usr);
        em.getTransaction().commit();
        em.close();
        if(updtUser == null) {
            return false;
        }
        return true;
    }

    @Override
    public List<User> getByGroup(Group group) throws ServiceException {
        Group fGroup;
        if (group == null || group.getId() == null) {
            throw new ServiceException("Group parameters not correct");
        }
        EntityManager em = emf.createEntityManager();
        fGroup = em.find(Group.class, group.getId());
        if (fGroup == null) {
            throw new ServiceException("Group not exist");
        }
        return new ArrayList<User>(fGroup.getUsers());
    }
}
