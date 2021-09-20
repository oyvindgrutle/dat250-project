import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class Main {

    private static final String PERSISTENCE_UNIT_NAME = "polls";
    private static EntityManagerFactory factory;

    public static void main(String[] args) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        /*
        Query q = em.createQuery("select p from Poll p");
        List<Poll> polls = q.getResultList();
        for (Poll poll : polls) {
            System.out.println(poll);
        }
        System.out.println("Size: " + polls.size());
        */

        em.getTransaction().begin();
        User user = new User();
        user.setUsername("bo");
        user.setAdmin(true);
        user.setName("Bo Aanes");
        em.persist(user);
        em.getTransaction().commit();

        em.getTransaction().begin();
        Poll poll = new Poll();
        poll.setQuestion("Kor dum e Øyvind?");
        poll.setPublic(true);
        poll.setUser(user);
        em.persist(poll);
        em.getTransaction().commit();

        em.close();
    }
}
