package demo.facts;

import java.io.Serializable;
import java.util.List;

public class Chocolate implements Serializable {

    public String name;

    public List<String> ingrediants;

    public String manufacturer;

    public float price;

    private float discount;

    private int ammount;

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

    public List<String> getIngridiants() {
        return ingrediants;
    }

    public void setIngridiants(List<String> ingridiants) {
        this.ingrediants = ingridiants;
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

    public Chocolate() {
    }

    public Chocolate(String name, List<String> ingridiants, String manufacturer, float price) {
        this.name = name;
        this.ingrediants = ingridiants;
        this.manufacturer = manufacturer;
        this.price = price;
        this.discount =0;
        this.ammount = 1;
    }

    public Chocolate(String name, List<String> ingrediants, String manufacturer, float price, float discount, int ammount) {
        this.name = name;
        this.ingrediants = ingrediants;
        this.manufacturer = manufacturer;
        this.price = price;
        this.discount = discount;
        this.ammount = ammount;
    }





    public Chocolate(Chocolate c) {
        this.name = c.getName();
        this.ingrediants = c.getIngridiants();
        this.manufacturer = c.getManufacturer();
        this.price = c.getPrice();
        this.discount = c.getDiscount();
        this.ammount = c.getAmmount();
    }

}





