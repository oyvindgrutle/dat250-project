package com.pollsen.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @ManyToOne
    private PollUser pollUser;

    public Poll() { }

    public Poll(String question, boolean isPublic, Date startTime, Date endTime, Integer accessCode, PollUser pollUser){
        this.question = question;
        this.isPublic = isPublic;
        this.startTime = startTime;
        this.endTime = endTime;
        this.accessCode = accessCode;
        this.pollUser = pollUser;

    }

}
