import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class PollUser {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String username;
    private String name;
    private boolean admin;

    @OneToMany(mappedBy = "pollUser")
    private final List<Poll> polls = new ArrayList<>();
}
