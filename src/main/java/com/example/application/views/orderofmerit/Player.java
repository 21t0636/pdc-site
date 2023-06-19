package com.example.application.views.orderofmerit;

public class Player {
    private int id;
    private String name;
    private String earnings;
    private String nickname;
    private String darts;
    private String nationality;
    private String image;

    public Player() {
    }

    public Player(int id, String name, String earnings, String nickname, String darts, String nationality, String image) {
        this.id = id;
        this.name = name;
        this.earnings = earnings;
        this.nickname = nickname;
        this.darts = darts;
        this.nationality = nationality;
        this.image = image;
    }

    // Getter und Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEarnings() {
        return earnings;
    }

    public void setEarnings(String earnings) {
        this.earnings = earnings;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDarts() {
        return darts;
    }

    public void setDarts(String darts) {
        this.darts = darts;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getImage () {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
