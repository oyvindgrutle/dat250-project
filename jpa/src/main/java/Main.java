import com.pollsen.domain.Poll;
import com.pollsen.domain.PollUser;
import com.pollsen.domain.PollUserDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Main {

    private static final String PERSISTENCE_UNIT_NAME = "polls";
    private static EntityManagerFactory factory;

    public static void main(String[] args) {
        // Add this to you presistence.xml if the database does not already exist:
        // <property name="eclipselink.ddl-generation" value="create-tables"/>


        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        /*
        Query q = em.createQuery("select p from jpa.Poll p");
        List<jpa.Poll> polls = q.getResultList();
        for (jpa.Poll poll : polls) {
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

        List<PollUser> users = PollUserDAO.getAll();
        for (PollUser user :
                users) {
            System.out.println(user.getName());
            System.out.println(user.getPolls().get(0).getQuestion());
        }
        System.out.println(users.size());

        em.close();
    }
}
