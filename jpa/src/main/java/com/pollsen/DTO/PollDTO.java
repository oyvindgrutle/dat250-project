package com.pollsen.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PollDTO {

    private Long id;
    private String question;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date startTime;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date endTime;
    private int numYes;
    private int numNo;

    public PollDTO(Long id, String question, Date startTime, Date endTime, int numYes, int numNo) {
        this.id = id;
        this.question = question;
        this.startTime = startTime;
        this.endTime = endTime;
        this.numYes = numYes;
        this.numNo = numNo;
    }

    public long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public int getNumYes() {
        return numYes;
    }

    public int getNumNo() {
        return numNo;
    }
}
