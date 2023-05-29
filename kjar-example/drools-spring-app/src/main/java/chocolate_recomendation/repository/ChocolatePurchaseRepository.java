package chocolate_recomendation.repository;

import demo.facts.Chocolate;
import demo.facts.ChocolateGrade;
import demo.facts.ChocolatePurchase;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository 
public class ChocolatePurchaseRepository {
    private static ChocolatePurchaseRepository instance = new ChocolatePurchaseRepository();


    private static List<ChocolatePurchase> chocolatePurchases ;



    public ChocolatePurchaseRepository() {
        this.initChocolatePurchases();
    }


    public static ChocolatePurchaseRepository getInstance() {
        return instance;
    }


    public static List<ChocolatePurchase> getChocolatePurchases() {
        return chocolatePurchases;
    }

    private void initChocolatePurchases(){

        chocolatePurchases = new ArrayList<>();
        ChocolatePurchase cp1 = new ChocolatePurchase("none@gmail.com","Mars",50);
   //     chocolatePurchases.add(cp1);

        ChocolatePurchase cp2 = new ChocolatePurchase("none@gmail.com","Bounty",50);
/*
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.MONTH, -5);


        Date ago = calendar.getTime();

        cp2.setDateOfPurchase(ago);
*/
     //   chocolatePurchases.add(cp2);

        ChocolatePurchase cp3 = new ChocolatePurchase("silver@gmail.com","Twix",50);
       /*
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.MONTH, -7);


        Date ago = calendar.getTime();

        cp3.setDateOfPurchase(ago);
*/
       // chocolatePurchases.add(cp3);

        ChocolatePurchase cp4 = new ChocolatePurchase("none@gmail.com","Twix",10);
       // chocolatePurchases.add(cp4);


    }

    public Object addChocolatePurchase(ChocolatePurchase chocolatePurchase){

        chocolatePurchases.add(chocolatePurchase);
        return chocolatePurchase;
    }






}
