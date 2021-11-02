package com.pollsen.DTO;

import java.util.List;

public class PollUserDTO {

    private Long id;
    private String username;
    private String name;
    private boolean admin;
    private List<Long> pollIdList;

    public PollUserDTO(Long id, String username, String name, boolean admin, List<Long> pollIdList) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.admin = admin;
        this.pollIdList = pollIdList;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public boolean isAdmin() {
        return admin;
    }

    public List<Long> getPollIdList() {
        return pollIdList;
    }
}
