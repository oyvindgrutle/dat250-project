package com.pollsen.domain;

import com.pollsen.repository.PollUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.transaction.Transactional;

import java.util.List;


@Service
@Transactional
public class PollUserDAO {

    @Autowired
    private PollUserRepository pollUserRepository;


    private static final String PERSISTENCE_UNIT_NAME = "polls";
    private static EntityManagerFactory factory;

    public void save(PollUser pollUser) {

    }



    public static void insertUser(PollUser pollUser) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(pollUser);
        em.getTransaction().commit();
    }

    public static void updateUser(PollUser pollUser) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        em.remove(pollUser);
        em.persist(pollUser);
        em.getTransaction().commit();

    }

    public static void deleteUser(PollUser pollUser) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        em.remove(pollUser);
        em.getTransaction().commit();
    }

    public static void deleteAll() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        em.clear();
        em.getTransaction().commit();
    }


    public static List<PollUser> getAll() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();


        Query q = em.createQuery("select pu from PollUser pu");
        List<PollUser> users = q.getResultList();

        em.close();

        return users;
    }
}
