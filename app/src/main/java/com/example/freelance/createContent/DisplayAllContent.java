package com.example.freelance.createContent;

public class DisplayAllContent {

     String firstName, lastName;
     Long age;

    public DisplayAllContent() {
    }

    public DisplayAllContent(String firstName, String lastName, Long age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getAge() {
        return age;
    }
}
