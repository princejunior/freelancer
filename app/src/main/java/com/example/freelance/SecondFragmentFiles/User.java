package com.example.freelance.SecondFragmentFiles;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class User {
    private String id;
    private String first_name;
    private String last_name;
    private String about_me_description;

    private ArrayList cart;

    public User() {
//        DataSnapshot.getValue(User.class);
    }
    public User(String id, String first_name, String last_name, String about_me_description) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.about_me_description = about_me_description;
    }

    public String getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getAbout_me_description() {
        return about_me_description;
    }
}
