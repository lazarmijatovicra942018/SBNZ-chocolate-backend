package demo.facts;

import java.io.Serializable;
import java.util.List;

public class Chocolate implements Serializable {

    public String name;

    public List<String> ingredients;

    public String manufacturer;

    public float price;

    private float discount;

    private int ammount;


    private double grade;


    private int myGrade;

    private double weight;

    private double sugarContent;

    private int nutriScore;

    private int score;

    private double calories;

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public int getMyGrade() {
        return myGrade;
    }

    public void setMyGrade(int myGrade) {
        this.myGrade = myGrade;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getSugarContent() {
        return sugarContent;
    }

    public void setSugarContent(double sugarContent) {
        this.sugarContent = sugarContent;
    }

    public int getNutriScore() {
        return nutriScore;
    }

    public void setNutriScore(int nutriScore) {
        this.nutriScore = nutriScore;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public int getAmmount() {
        return ammount;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        manufacturer = manufacturer;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getScore() {
        return score;
    }


    public boolean areIngredientsUnique(Chocolate chocolate){

        if(this == chocolate){
            return  true;}
        for(String ingredient : chocolate.ingredients) {
            if(this.ingredients.contains(ingredient)){return false;}
        }
        return true;

    }

    public void setScore(int score) {
        this.score = score;
    }

    public Chocolate() {
    }

    public Chocolate(String name, List<String> ingridiants, String manufacturer, float price) {
        this.name = name;
        this.ingredients = ingridiants;
        this.manufacturer = manufacturer;
        this.price = price;
        this.discount =0;
        this.ammount = 0 ;
        this.score = 0;
        this.nutriScore = 0;
    }

    public Chocolate(String name, List<String> ingrediants, String manufacturer, float price, float discount, int ammount) {
        this.name = name;
        this.ingredients = ingrediants;
        this.manufacturer = manufacturer;
        this.price = price;
        this.discount = discount;
        this.ammount = ammount;
        this.score = 0;
        this.nutriScore = 0;
    }





    public Chocolate(Chocolate c) {
        this.name = c.name;
        this.ingredients = c.ingredients;
        this.manufacturer = c.manufacturer;
        this.price = c.price;
        this.discount = c.discount;
        this.ammount = c.ammount;
        this.grade = c.grade;
        this.myGrade = c.myGrade;
        this.weight = c.weight;
        this.sugarContent = c.sugarContent;
        this.nutriScore = c.nutriScore;
        this.score = c.score;
        this.calories = c.calories;
    }


    public Chocolate(String name, List<String> ingredients, String manufacturer, float price,double weight, double sugarContent, int nutriScore) {
        this.name = name;
        this.ingredients = ingredients;
        this.manufacturer = manufacturer;
        this.price = price;
        this.discount = 0;
        this.ammount = 0;
        this.weight = weight;
        this.sugarContent = sugarContent;
        this.nutriScore = nutriScore;
        this.score = 0;




    }

    public Chocolate(String name, List<String> ingredients, String manufacturer, float price,double weight, double sugarContent, int nutriScore, double calories) {
        this.name = name;
        this.ingredients = ingredients;
        this.manufacturer = manufacturer;
        this.price = price;
        this.discount = discount;
        this.ammount = ammount;
        this.grade = grade;
        this.myGrade = myGrade;
        this.weight = weight;
        this.sugarContent = sugarContent;
        this.nutriScore = nutriScore;
        this.score = 0;
        this.calories = calories;
        this.discount = 0;
        this.ammount = 0;
    }
}





