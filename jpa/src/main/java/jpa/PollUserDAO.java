package jpa;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import java.util.List;

public class PollUserDAO {

    private static final String PERSISTENCE_UNIT_NAME = "polls";
    private static EntityManagerFactory factory;

    public void insertUser(PollUser pollUser) {

    }

    public void updateUser(PollUser pollUser) {

    }

    public void deleteUser(PollUser pollUser) {

    }

    public static List<PollUser> getAll() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();


        Query q = em.createQuery("select pu from PollUser pu");
        List<PollUser> users = q.getResultList();

        em.close();

        return users;
    }

    public PollUser getUser(PollUser pollUser) {
        return null;
    }

    public PollUser getUserById(Long id) {
        return null;
    }
}
