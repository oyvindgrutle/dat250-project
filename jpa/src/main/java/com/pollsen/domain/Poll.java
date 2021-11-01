package com.pollsen.domain;

import lombok.Data;

import javax.persistence.*;
import java.security.SecureRandom;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Data
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String question;
    private Integer accessCode;
    private boolean isPublic;
    private Date startTime;
    private Date endTime;

    @ManyToOne
    private PollUser pollUser;

    public Poll() { }

    public Poll(String question, boolean isPublic, Integer accessCode, PollUser pollUser){
        this.question = question;
        this.isPublic = isPublic;
        this.accessCode = accessCode;
        this.pollUser = pollUser;

    }

}
