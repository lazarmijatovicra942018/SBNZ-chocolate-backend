package chocolate_recomendation.repository;
import demo.facts.Chocolate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ChocolateRepository {

    private List<String> ingridians;
    public List<Chocolate> chocolates;
    public ChocolateRepository(){
        initChocolates();
    }

    public List<String> getIngridians() {
        return ingridians;
    }

    public void setIngridians(List<String> ingridians) {
        this.ingridians = ingridians;
    }

    public List<Chocolate> getChocolates() {
        return chocolates;
    }

    public void setChocolates(List<Chocolate> chocolates) {
        this.chocolates = chocolates;
    }

    private void initIngridians(){
        ingridians =new ArrayList<>();
        ingridians.add("crna cokolada");
        ingridians.add("bela cokolada");
        ingridians.add("mlecna cokolada");
        ingridians.add("karamela");
        ingridians.add("keks");
        ingridians.add("jagoda");
        ingridians.add("nugat");
        ingridians.add("kikiriki");
        ingridians.add("pistaci");

    }
    public void initChocolates(){
        this.initIngridians();
        chocolates = new ArrayList<>();
        List<String> ingridians1= new ArrayList<>();
        ingridians1.add(ingridians.get(2));
        ingridians1.add(ingridians.get(3));
        Chocolate c1 = new Chocolate("Mars", ingridians1,"Mars" , 50);
        c1.setAmmount(6);
        chocolates.add(c1);


    }










}
