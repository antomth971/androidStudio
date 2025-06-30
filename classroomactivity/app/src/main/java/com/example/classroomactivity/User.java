package com.example.classroomactivity;

import java.io.Serializable;

public class User implements Serializable {
    String user;
    String login;

    public User(String user,String login){
        this.login=login;
        this.user=user;
    }

    public String getLogin(){
        return login;
    }

    public String getUser(){
        return user;
    }



}
