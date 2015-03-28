package ru.returnonintelligence.testask.beans;

import ru.returnonintelligence.testask.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;;


/**
 * Created by Антон on 28.03.2015.
 */
public class UserBean {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("jdbc_unit");
    private EntityManager em = emf.createEntityManager();

    public User add(User user){
       // em.getTransaction().begin();
        User us = em.merge(user);
       // em.getTransaction().commit();
       // em.close();
        return us;
    }
}
