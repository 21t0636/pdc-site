package com.example.application.data.entity;

import jakarta.persistence.Entity;
import java.time.LocalDate;

@Entity
public class SamplePerson extends AbstractEntity {

    private String tournaments;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;
    private boolean age;
    private boolean rules;
    private boolean allowPhotos;

    private String playerHand;

    public String getTournaments() {
        return tournaments;
    }
    public void setTournaments(String tournaments) {
        this.tournaments = tournaments;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public boolean getAge() {
        return age;
    }
    public void setAge(boolean age) {
        this.age = age;
    }
    public String getPlayerHand() {
        return playerHand;
    }
    public void setPlayerHand(String playerHand) {
        this.playerHand = playerHand;
    }
    public boolean getRules() {
        return rules;
    }
    public void setRules(boolean rules) {
        this.rules = rules;
    }
    public boolean getAllowPhotos() {
        return allowPhotos;
    }
    public void setAllowPhotos(boolean allowPhotos) {
        this.allowPhotos = allowPhotos;
    }
}
