package com.pollsen.DAO;

import com.pollsen.domain.Poll;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class PollDAO {

    private static final String PERSISTENCE_UNIT_NAME = "polls";
    private static EntityManagerFactory factory;

    public static void insertPoll(Poll poll) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(poll);
        em.getTransaction().commit();
    }

    public static void updatePoll(Poll poll) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        em.remove(poll);
        em.persist(poll);   
        em.getTransaction().commit();
    }

    public static void deletePoll(Poll poll) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        em.remove(poll);
        em.getTransaction().commit();
    }

    public static List<Poll> getAll() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();


        Query q = em.createQuery("select p from Poll p");
        List<Poll> polls = q.getResultList();

        em.close();

        return polls;
    }

}
