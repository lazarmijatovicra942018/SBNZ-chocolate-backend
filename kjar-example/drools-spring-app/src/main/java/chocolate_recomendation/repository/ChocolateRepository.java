package chocolate_recomendation.repository;
import demo.facts.Chocolate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ChocolateRepository {

    private static ChocolateRepository instance = new ChocolateRepository();
    private static List<String> ingredients;
    public static List<Chocolate> chocolates;

    public ChocolateRepository() {
        this.initChocolates();
    }

    public static List<String> getIngredients() {
        return ingredients;
    }

    public static void setIngredients(List<String> ingredients) {
        ChocolateRepository.ingredients = ingredients;
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
        ingredients =new ArrayList<>();
        ingredients.add("crna cokolada");
        ingredients.add("bela cokolada");
        ingredients.add("mlecna cokolada");
        ingredients.add("karamela");//3
        ingredients.add("keks");
        ingredients.add("jagoda");//5
        ingredients.add("nugat");
        ingredients.add("kikiriki");
        ingredients.add("pistaci");
        ingredients.add("kokos");//9
        ingredients.add("mleko");//10



    }
    public void initChocolates(){
        this.initIngredians();
        chocolates = new ArrayList<>();
        List<String> ingredients1= new ArrayList<>();
        ingredients1.add(ingredients.get(2));
        ingredients1.add(ingredients.get(3));
        Chocolate c1 = new Chocolate("Mars", ingredients1,"Mars" , 50);
        chocolates.add(c1);

        List<String> ingredients2= new ArrayList<>();
        ingredients2.add(ingredients.get(2));
        ingredients2.add(ingredients.get(9));
        Chocolate c2 = new Chocolate("Bounty", ingredients2,"Mars" , 75);
        chocolates.add(c2);


        List<String> ingredients3= new ArrayList<>();
        ingredients3.add(ingredients.get(2));
        ingredients3.add(ingredients.get(3));
        ingredients3.add(ingredients.get(4));

        Chocolate c3 = new Chocolate("Twix", ingredients3,"Mars" , 75);
        chocolates.add(c3);

        List<String> ingrediants4= new ArrayList<>();
        ingrediants4.add(ingredients.get(2));
        ingrediants4.add(ingredients.get(10));
        Chocolate c4 = new Chocolate("Kidy sa mlekom", ingrediants4,"Pionir" , 33);
        chocolates.add(c4);


        List<String> ingredients5= new ArrayList<>();
        ingredients5.add(ingredients.get(2));
        ingredients5.add(ingredients.get(5));
        Chocolate c5 = new Chocolate("Kidy sa jagodom", ingredients5,"Pionir" , 33);
        chocolates.add(c5);



    }










}
