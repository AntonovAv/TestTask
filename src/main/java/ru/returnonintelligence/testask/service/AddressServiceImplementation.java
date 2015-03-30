package ru.returnonintelligence.testask.service;

import ru.returnonintelligence.testask.entities.Address;
import ru.returnonintelligence.testask.service.interfaces.AddressService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.validation.ValidatorFactory;
import java.util.List;

/**
 * Created by Anton on 29.03.2015.
 * Service for work with Address entity
 */
public class AddressServiceImplementation extends ValidateServiceImplementation implements AddressService{

    EntityManagerFactory emf;

    public AddressServiceImplementation(EntityManagerFactory emf, ValidatorFactory vf) {
        super(vf);
        this.emf = emf;
    }

    @Override
    public Long create(Address address) throws ServiceException {
        if (address == null) {
            throw new ServiceException("Addresses parameters for create are incorrect");
        }
        if (!validate(address)) {
            throw new ServiceException("Addresses parameters for create are incorrect");
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Address gr = em.merge(address);
        em.getTransaction().commit();
        em.close();
        if (gr == null) {
            return -1l;
        }
        return gr.getId();
    }

    @Override
    public List<Address> getAll() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Address.getAll", Address.class);
        return query.getResultList();
    }

    @Override
    public Boolean delete(Address address) throws ServiceException {
        Address delAddr;
        if (address == null || address.getId() == null) {
            throw new ServiceException("Address for delete not found");
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        delAddr = em.find(Address.class, address.getId());
        em.remove(delAddr);
        em.getTransaction().commit();
        em.close();
        return true;
    }

    @Override
    public Boolean update(Address address) throws ServiceException {
        if (address == null || address.getId() == null) {
            throw new ServiceException("Address not updated");
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Address updAddr = em.merge(address);
        em.getTransaction().commit();
        em.close();
        if(updAddr == null) {
            return false;
        }
        return true;
    }

    @Override
    public Address find(Address address) throws ServiceException {
        if (address == null) {
            throw new ServiceException("Address parameters for find are incorrect");
        }
        if (address.getId() == null) {
            throw new ServiceException("Address id for find not found");
        }
        EntityManager em = emf.createEntityManager();
        Address addrF = em.find(Address.class, address.getId());
        em.close();
        return addrF;
    }
}
