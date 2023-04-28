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
        ingrediants.add("karamela");
        ingrediants.add("keks");
        ingrediants.add("jagoda");
        ingrediants.add("nugat");
        ingrediants.add("kikiriki");
        ingrediants.add("pistaci");

    }
    public void initChocolates(){
        this.initIngredians();
        chocolates = new ArrayList<>();
        List<String> ingrediants1= new ArrayList<>();
        ingrediants1.add(ingrediants.get(2));
        ingrediants1.add(ingrediants.get(3));
        Chocolate c1 = new Chocolate("Mars", ingrediants1,"Mars" , 50);
        c1.setAmmount(6);
        chocolates.add(c1);


    }










}
