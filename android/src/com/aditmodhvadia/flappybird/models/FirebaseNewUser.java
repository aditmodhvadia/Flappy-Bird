package com.aditmodhvadia.flappybird.models;

public class FirebaseNewUser {

    private String email, password, userName, photoUrl, highScore;

    public FirebaseNewUser(String email, String password, String userName, String photoUrl, String highScore) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.photoUrl = photoUrl;
        this.highScore = highScore;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getHighScore() {
        return highScore;
    }

    public void setHighScore(String highScore) {
        this.highScore = highScore;
    }
}
