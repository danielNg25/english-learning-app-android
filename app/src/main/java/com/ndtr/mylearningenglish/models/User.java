package com.ndtr.mylearningenglish.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    private String userName;
    private String password;
    private String fullName;
    private String email;

    public User(){}

    public User(String userName, String password, String fullName, String email){
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
    }



    public boolean isEmpty(){
        if (userName.isEmpty()||password.isEmpty()){
            return false;
        }
        return true;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
