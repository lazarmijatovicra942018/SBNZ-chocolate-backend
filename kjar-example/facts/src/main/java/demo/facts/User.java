package demo.facts;

import demo.facts.UserRank;
import demo.facts.UserType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable{

    private String email;
    private String password;
    private String name;
    private String surname;

    private String phoneNum;

    private UserType userType;

    private UserRank userRank;


    private boolean isFirstTimeLogin;


    private List<String>  favouriteIngredients = new ArrayList<>();


    private List<String> dislikedIngredients = new ArrayList<>();


    private List<ChocolateGrade> grading = new ArrayList<>();







    public User(String email, String password, String name, String surname, String phoneNum, UserType userType, UserRank userRank) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phoneNum = phoneNum;
        this.userType = userType;
        this.userRank = userRank;
        this.isFirstTimeLogin = true;


    }


    public User(User user) {
        this.email = user.email;
        this.password = user.password;
        this.name = user.name;
        this.surname = user.surname;
        this.phoneNum = user.phoneNum;
        this.userType = user.userType;
        this.userRank = user.userRank;
        this.isFirstTimeLogin = user.isFirstTimeLogin;
        this.favouriteIngredients = user.favouriteIngredients;
        this.dislikedIngredients = user.dislikedIngredients;
        this.grading = user.grading;
    }

    public User() {
    }


    public void Loging(){
        isFirstTimeLogin = false;
    }



    public boolean isFirstTimeLogin() {
        return isFirstTimeLogin;
    }

    public void setFirstTimeLogin(boolean firstTimeLogin) {
        isFirstTimeLogin = firstTimeLogin;
    }

    public List<String> getFavouriteIngredients() {
        return favouriteIngredients;
    }


    public void addFavouriteIngredient(String ingredient) {
        favouriteIngredients.add(ingredient);
    }

    public void removeFavouriteIngredient(String ingredient) {
        favouriteIngredients.remove(ingredient);
    }

    public void addDislikedIngredient(String ingredient) {
        dislikedIngredients.add(ingredient);
    }

    public void removeDislikedIngredient(String ingredient) {
        dislikedIngredients.remove(ingredient);
    }


    public void setFavouriteIngredients(List<String> favouriteIngredients) {
        this.favouriteIngredients = favouriteIngredients;
    }

    public List<String> getDislikedIngredients() {
        return dislikedIngredients;
    }

    public void setDislikedIngredients(List<String> dislikedIngredients) {
        this.dislikedIngredients = dislikedIngredients;
    }

    public List<ChocolateGrade> getGrading() {
        return grading;
    }

    public void setGrading(List<ChocolateGrade> grading) {
        this.grading = grading;
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
