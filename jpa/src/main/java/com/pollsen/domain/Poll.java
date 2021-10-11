package com.pollsen.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String question;
    private int accessCode;
    private boolean isPublic;
    private Date startTime;
    private Date endTime;

    @OneToMany(mappedBy = "poll")
    private final List<Answer> answers = new ArrayList<>();

    @ManyToOne
    private PollUser pollUser;

    public Poll() { }

    public Poll(String question, boolean isPublic, PollUser pollUser){
        this.question = question;
        this.isPublic = isPublic;
        this.pollUser = pollUser;
    }

}
