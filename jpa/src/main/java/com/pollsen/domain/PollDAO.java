package com.pollsen.domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class PollDAO {

    private static final String PERSISTENCE_UNIT_NAME = "polls";
    private static EntityManagerFactory factory;

    public void insertPoll(Poll poll) {

    }

    public void updatePoll(Poll poll) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();



    }

    public static List<Poll> getAll() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();


        Query q = em.createQuery("select p from Poll p");
        List<Poll> polls = q.getResultList();

        em.close();

        return polls;
    }

    public Poll getPoll(Poll poll) {
        return null;
    }

    public Poll getPollById(long id) {
        return null;
    }

    public List<Answer> getPollAnswers(Poll poll) {
        return null;
    }


}
