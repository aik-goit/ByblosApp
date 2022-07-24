package com.example.byblos;

public class User {

    public String firstName, lastName, email, role;

    public User(){

    }

    public User(String fN, String lN, String email, String role){
        firstName = fN;
        lastName = lN;
        this.email = email;
        this.role = role;
    }

    public String getRole(){
        return this.role;
    }
}
