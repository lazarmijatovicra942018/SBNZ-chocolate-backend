package demo.facts;

import demo.facts.UserRank;
import demo.facts.UserType;

import java.io.Serializable;

public class User implements Serializable{

    private String email;
    private String password;
    private String name;
    private String surname;
    private String phoneNum;

    private UserType userType;

    private UserRank userRank;




    public User(String email, String password, String name, String surname, String phoneNum, UserType userType, UserRank userRank) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phoneNum = phoneNum;
        this.userType = userType;
        this.userRank = userRank;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public UserType getUserType() {
        return userType;
    }

    public UserRank getUserRank() {
        return userRank;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setUserRank(UserRank userRank) {
        this.userRank = userRank;
    }
}
