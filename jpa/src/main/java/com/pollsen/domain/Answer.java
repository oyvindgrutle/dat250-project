package com.pollsen.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private boolean answer;

    @ManyToOne
    private Poll poll;

    public Answer() { }

    public Answer(boolean answer, Poll poll) {
        this.answer = answer;
        this.poll = poll;
    }

}
