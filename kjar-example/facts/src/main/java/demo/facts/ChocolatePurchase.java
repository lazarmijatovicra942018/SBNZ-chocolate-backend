package demo.facts;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.kie.api.definition.type.Role;
@Role(Role.Type.EVENT)
public class ChocolatePurchase implements Serializable {

    private String  purchaseId;
    private String userEmail ;
    private String chocolateName;
    private int ammount;

    private float price;

    private Date dateOfPurchase;

    public ChocolatePurchase() {
    }


    public ChocolatePurchase( String userEmail, String chocolateName, int ammount) {
        this.dateOfPurchase = new Date();
        this.userEmail = userEmail;
        this.chocolateName = chocolateName;
        this.ammount = ammount;
        this.purchaseId = generateId(this.userEmail,this.chocolateName,this.dateOfPurchase);
        this.price = 50;

    }

    public ChocolatePurchase( String userEmail, String chocolateName, int ammount, float price) {
        this.dateOfPurchase = new Date();
        this.userEmail = userEmail;
        this.chocolateName = chocolateName;
        this.ammount = ammount;
        this.purchaseId = generateId(this.userEmail,this.chocolateName,this.dateOfPurchase);
        this.price = price;

    }

    public ChocolatePurchase( String userEmail, String chocolateName, int ammount , Date dateOfPurchase, float price) {
        this.dateOfPurchase = dateOfPurchase;
        this.userEmail = userEmail;
        this.chocolateName = chocolateName;
        this.ammount = ammount;
        this.purchaseId = generateId(this.userEmail,this.chocolateName,this.dateOfPurchase);
        this.price = price;

    }





    public String generateId(String userEmail , String chocolateName , Date dateOfPurchase)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyyDDmmhhMMss");
        String date = dateFormat.format(dateOfPurchase);
        return userEmail + chocolateName+date;
    }




    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getChocolateName() {
        return chocolateName;
    }

    public void setChocolateName(String chocolateName) {
        this.chocolateName = chocolateName;
    }

    public int getAmmount() {
        return ammount;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
