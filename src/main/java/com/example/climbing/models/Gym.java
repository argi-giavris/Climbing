package com.example.climbing.models;

public class Gym {
    Integer gymId;
    String name;
    String location;
    String ownerEmail;

    public Gym(Integer gymId, String name, String location, String ownerEmail) {
        this.gymId = gymId;
        this.name = name;
        this.location = location;
        this.ownerEmail = ownerEmail;
    }

    public Gym(){}

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public Gym(Integer gymId, String name, String location){
        this.gymId = gymId;
        this.name = name;
        this.location = location;
    }

    public Integer getGymId() {
        return gymId;
    }

    public void setGymId(Integer gymId) {
        this.gymId = gymId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
