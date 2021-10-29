package com.example.freelance;

import java.util.ArrayList;

public class User {
    private int id;
    private String first_name;
    private String last_name;
    private String about_me_description;

    private ArrayList cart;

    public User(int id, String first_name, String last_name, String about_me_description) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.about_me_description = about_me_description;
    }
}
