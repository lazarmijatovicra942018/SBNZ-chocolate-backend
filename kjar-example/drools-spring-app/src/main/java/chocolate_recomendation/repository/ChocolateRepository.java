package chocolate_recomendation.repository;
import demo.facts.Chocolate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ChocolateRepository {

    private static ChocolateRepository instance = new ChocolateRepository();
    private List<String> ingrediants;
    public List<Chocolate> chocolates;

    public ChocolateRepository() {
        this.initChocolates();
    }

    public List<String> getIngrediants() {
        return ingrediants;
    }

    public void setIngrediants(List<String> ingredians) {
        this.ingrediants = ingredians;
    }

    public List<Chocolate> getChocolates() {
        return chocolates;
    }

    public void setChocolates(List<Chocolate> chocolates) {
        this.chocolates = chocolates;
    }

    public static ChocolateRepository getInstance() {
        return instance;
    }

    private void initIngredians(){
        ingrediants =new ArrayList<>();
        ingrediants.add("crna cokolada");
        ingrediants.add("bela cokolada");
        ingrediants.add("mlecna cokolada");
        ingrediants.add("karamela");//3
        ingrediants.add("keks");
        ingrediants.add("jagoda");//5
        ingrediants.add("nugat");
        ingrediants.add("kikiriki");
        ingrediants.add("pistaci");
        ingrediants.add("kokos");//9
        ingrediants.add("mleko");//10



    }
    public void initChocolates(){
        this.initIngredians();
        chocolates = new ArrayList<>();
        List<String> ingrediants1= new ArrayList<>();
        ingrediants1.add(ingrediants.get(2));
        ingrediants1.add(ingrediants.get(3));
        Chocolate c1 = new Chocolate("Mars", ingrediants1,"Mars" , 50);
        chocolates.add(c1);

        List<String> ingrediants2= new ArrayList<>();
        ingrediants2.add(ingrediants.get(2));
        ingrediants2.add(ingrediants.get(9));
        Chocolate c2 = new Chocolate("Bounty", ingrediants2,"Mars" , 75);
        chocolates.add(c2);


        List<String> ingrediants3= new ArrayList<>();
        ingrediants3.add(ingrediants.get(2));
        ingrediants3.add(ingrediants.get(3));
        ingrediants3.add(ingrediants.get(4));

        Chocolate c3 = new Chocolate("Twix", ingrediants3,"Mars" , 75);
        chocolates.add(c3);

        List<String> ingrediants4= new ArrayList<>();
        ingrediants4.add(ingrediants.get(2));
        ingrediants4.add(ingrediants.get(10));
        Chocolate c4 = new Chocolate("Kidy sa mlekom", ingrediants4,"Pionir" , 33);
        chocolates.add(c4);


        List<String> ingrediants5= new ArrayList<>();
        ingrediants5.add(ingrediants.get(2));
        ingrediants5.add(ingrediants.get(5));
        Chocolate c5 = new Chocolate("Kidy sa jagodom", ingrediants5,"Pionir" , 33);
        chocolates.add(c5);



    }










}
