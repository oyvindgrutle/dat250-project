import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String name;
    private boolean admin;

    @OneToMany(mappedBy = "user")
    private final List<Poll> polls = new ArrayList<>();
}
