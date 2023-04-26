package demo.facts;

import java.io.Serializable;
import java.util.List;

public class Chocolate implements Serializable {

    public String name;

    public List<String> ingridiants;

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
        return ingridiants;
    }

    public void setIngridiants(List<String> ingridiants) {
        this.ingridiants = ingridiants;
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
        this.ingridiants = ingridiants;
        this.manufacturer = manufacturer;
        this.price = price;
        this.discount =0;
        this.ammount = 1;
    }

    public Chocolate(String name, List<String> ingridiants, String manufacturer, float price, float discount, int ammount) {
        this.name = name;
        this.ingridiants = ingridiants;
        this.manufacturer = manufacturer;
        this.price = price;
        this.discount = discount;
        this.ammount = ammount;
    }





    public Chocolate(Chocolate c) {
        this.name = c.getName();
        this.ingridiants = c.getIngridiants();
        this.manufacturer = c.getManufacturer();
        this.price = c.getPrice();
        this.discount = c.getDiscount();
        this.ammount = c.getAmmount();
    }

}





