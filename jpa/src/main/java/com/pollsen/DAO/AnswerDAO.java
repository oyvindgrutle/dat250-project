package com.pollsen.DAO;

import com.pollsen.domain.Answer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class AnswerDAO {

    private static final String PERSISTENCE_UNIT_NAME = "polls";
    private static EntityManagerFactory factory;

    public static void insertAnswer(Answer answer) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(answer);
        em.getTransaction().commit();
    }

    public static void updateAnswer(Answer answer) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        em.remove(answer);
        em.persist(answer);
        em.getTransaction().commit();
    }

    public static void deleteAnswer(Answer answer) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        em.remove(answer);
        em.getTransaction().commit();
    }

    public static List<Answer> getAll() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();

        Query q = em.createQuery("select a from Answer a");

        List<Answer> answers = q.getResultList();

        return answers;
    }
}
