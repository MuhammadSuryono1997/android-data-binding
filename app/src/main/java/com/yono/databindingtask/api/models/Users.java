package com.yono.databindingtask.api.models;

import com.google.gson.annotations.SerializedName;

public class Users {

    @SerializedName("email")
    private String emailUsers;

    @SerializedName("first_name")
    private String firstNameUsers;

    @SerializedName("last_name")
    private String lastNameUsers;

    @SerializedName("avatar")
    private String imageUsers;

    public String getEmailUsers() {
        return emailUsers;
    }

    public void setEmailUsers(String emailUsers) {
        this.emailUsers = emailUsers;
    }

    public String getFirstNameUsers() {
        return firstNameUsers;
    }

    public void setFirstNameUsers(String firstNameUsers) {
        this.firstNameUsers = firstNameUsers;
    }

    public String getLastNameUsers() {
        return lastNameUsers;
    }

    public void setLastNameUsers(String lastNameUsers) {
        this.lastNameUsers = lastNameUsers;
    }

    public String getImageUsers() {
        return imageUsers;
    }

    public void setImageUsers(String imageUsers) {
        this.imageUsers = imageUsers;
    }
}
