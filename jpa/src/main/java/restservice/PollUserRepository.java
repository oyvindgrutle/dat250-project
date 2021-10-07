package restservice;

import jpa.Poll;
import jpa.PollUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PollUserRepository extends CrudRepository<PollUser, Long> {

    List<PollUser> findByUsername(@Param("name") String username);
}