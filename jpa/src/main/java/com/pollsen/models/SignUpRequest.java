package com.pollsen.models;

import lombok.Data;

@Data
public class SignUpRequest {

    private String username;
    private String password;
    private String name;
    private boolean admin;

    public SignUpRequest(String username, String password, String name, boolean admin){
        this.username = username;
        this.password = password;
        this.name = name;
        this.admin = admin;
    }

    public SignUpRequest(){

    }

}
