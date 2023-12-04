package com.example.climbing.models;

public class    User {
    Integer id;
    String firstName;
    String lastName;
    String password;
    String email;
    Role role;



    public User(String firstName, String lastName, String password, String email, Role role){
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public User(){}

    public  User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getFirstName(){ return this.firstName;}
    public String getLastName(){ return this.lastName;}
    public String getPassword(){ return this.password;}
    public String getEmail(){ return this.email;}
    public Integer getId() {return this.id;}
    public  Role getRole(){return this.role;}

    public void setId(Integer id){ this.id = id;}

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(Role role) {
        this.role = role;
    }



}
