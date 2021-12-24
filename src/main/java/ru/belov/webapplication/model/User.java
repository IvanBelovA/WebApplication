package ru.belov.webapplication.model;

import lombok.Data;

@Data
public class User {

    private int userId;
    private String name;
    private String pass;
    private String mail;

    public User() {
    }

    public User(int userId, String name, String pass, String mail) {
        this.userId = userId;
        this.name = name;
        this.pass = pass;
        this.mail = mail;
    }

}
