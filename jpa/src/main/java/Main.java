import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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

        PollUser pollUser = new PollUser();
        pollUser.setUsername("bo");
        pollUser.setAdmin(true);
        pollUser.setName("Bo Aanes");
        em.persist(pollUser);


        Poll poll = new Poll();
        poll.setQuestion("Kor dum e Øyvind?");
        poll.setPublic(true);
        poll.setPollUser(pollUser);
        em.persist(poll);
        pollUser.getPolls().add(poll);
        em.persist(poll);
        em.persist(pollUser);

        em.getTransaction().commit();

        em.close();
    }
}