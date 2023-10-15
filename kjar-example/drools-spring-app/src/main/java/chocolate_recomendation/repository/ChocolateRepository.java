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
        chocolates =  new ArrayList<>();
        this.initChocolates();

    }

    public static List<String> getIngredients() {
        return ingredients;
    }

    public List<Chocolate> getChocolates() {
        return chocolates;
    }

    public static ChocolateRepository getInstance() {
        return instance;
    }

    private void initIngredians(){
        ingredients =new ArrayList<>();
        ingredients.add("kakao");//0
        ingredients.add("mleko");//1
        ingredients.add("lešnik");//2
        ingredients.add("kikiriki");//3
        ingredients.add("karamela");//4
        ingredients.add("nugat");//5
        ingredients.add("mlečna čokolada");//6
        ingredients.add("kokos");//7
        ingredients.add("keks");//8
        ingredients.add("crna čokolada");//9
        ingredients.add("jezgrasto voće");//10
        ingredients.add("narandža");//11
        ingredients.add("bela čokolada");//12
        ingredients.add("jagoda");//13
        ingredients.add("pirinač");//14
        ingredients.add("badem");//15






        /*
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
        ingredients.add("lesnik");//11
        ingredients.add("pistaci");//12
        ingredients.add("mentol");//13
        */



    }
    public void initChocolates(){
        this.initIngredians();
        chocolates = new ArrayList<>();
        List<String> ingredients1= new ArrayList<>();
        ingredients1.add(ingredients.get(0));
        ingredients1.add(ingredients.get(1));
        ingredients1.add(ingredients.get(2));
        Chocolate c1 = new Chocolate("Cipiripi", ingredients1,"Nestle" , 82.99f , 80 , 46.48 , 1, 428.80);
        chocolates.add(c1);


        List<String> ingredients2= new ArrayList<>();
        ingredients2.add(ingredients.get(3));
        ingredients2.add(ingredients.get(4));
        ingredients2.add(ingredients.get(5));
        ingredients2.add(ingredients.get(6));
        Chocolate c2 = new Chocolate("Snikers", ingredients2,"Mars" , 79.99f , 50 , 25.40 , 2, 242.50);
        chocolates.add(c2);

        List<String> ingredients3= new ArrayList<>();
        ingredients3.add(ingredients.get(1));
        ingredients3.add(ingredients.get(4));
        ingredients3.add(ingredients.get(5));
        ingredients3.add(ingredients.get(6));
        Chocolate c3 = new Chocolate("Milky Way", ingredients3,"Mars" , 55.79f , 21.50 , 13.87 , 2, 97.61);
        chocolates.add(c3);

        List<String> ingredients4= new ArrayList<>();
        ingredients4.add(ingredients.get(6));
        ingredients4.add(ingredients.get(7));
        Chocolate c4 = new Chocolate("Bounty", ingredients4,"Mars" , 87.99f , 57.00 , 27.47 , 3 , 277.59);
        chocolates.add(c4);

        List<String> ingredients5= new ArrayList<>();
        ingredients5.add(ingredients.get(4));
        ingredients5.add(ingredients.get(6));
        ingredients5.add(ingredients.get(8));
        Chocolate c5 = new Chocolate("Twix", ingredients5,"Mars" , 81.59f , 50.00 , 24.3 , 2 , 247.00);
        chocolates.add(c5);

        List<String> ingredients6= new ArrayList<>();
        ingredients6.add(ingredients.get(4));
        ingredients6.add(ingredients.get(5));
        ingredients6.add(ingredients.get(6));
        Chocolate c6 = new Chocolate("Mars", ingredients6,"Mars" , 79.99f , 47.00 , 29.23 , 1 , 212.00);
        chocolates.add(c6);

        List<String> ingredients7= new ArrayList<>();
        ingredients7.add(ingredients.get(1));
        ingredients7.add(ingredients.get(3));
        ingredients7.add(ingredients.get(6));
        ingredients7.add(ingredients.get(10));
        Chocolate c7 = new Chocolate("Za-za", ingredients7,"Štark" , 39.99f , 25.00 , 9.38 , 2 , 129.00);
        chocolates.add(c7);

        List<String> ingredients8= new ArrayList<>();
        ingredients8.add(ingredients.get(3));
        ingredients8.add(ingredients.get(8));
        Chocolate c8 = new Chocolate("Tamna čokolada", ingredients8,"Schogetten" , 213.99f , 100.00 , 47 , 3 , 527.00);
        chocolates.add(c8);

        List<String> ingredients9= new ArrayList<>();
        ingredients9.add(ingredients.get(9));
        ingredients9.add(ingredients.get(10));
        ingredients9.add(ingredients.get(11));
        Chocolate c9 = new Chocolate("Najlepše želje selection 75%", ingredients9,"Štark" , 169.99f , 75.00 , 21 , 3 , 388.50);
        chocolates.add(c9);

        List<String> ingredients10= new ArrayList<>();
        ingredients10.add(ingredients.get(12));
        Chocolate c10 = new Chocolate("Bela čokolada", ingredients10,"Milka" , 186.99f , 100.00 , 64 , 1 , 530.00);
        chocolates.add(c10);

        List<String> ingredients11 = new ArrayList<>();
        ingredients11.add(ingredients.get(2));
        ingredients11.add(ingredients.get(6));
        ingredients11.add(ingredients.get(10));
        Chocolate c11 = new Chocolate("Lešnik", ingredients11,"Milka" , 142.99f , 80.00 , 42.40 , 2 , 434.40);
        chocolates.add(c11);

        List<String> ingredients12 = new ArrayList<>();
        ingredients12.add(ingredients.get(0));
        ingredients12.add(ingredients.get(1));
        Chocolate c12 = new Chocolate("Čokolada Kinder", ingredients12,"Kinder" , 151.99f , 100.00 , 53.30 , 2 , 566.00);
        chocolates.add(c12);


        List<String> ingredients13 = new ArrayList<>();
        ingredients13.add(ingredients.get(6));
        ingredients13.add(ingredients.get(13));
        Chocolate c13 = new Chocolate("Jagoda", ingredients13,"Milka" , 189.99f , 100.00 , 53.30 , 2 , 552.00);
        chocolates.add(c13);

        List<String> ingredients14 = new ArrayList<>();
        ingredients14.add(ingredients.get(1));
        ingredients14.add(ingredients.get(6));
        ingredients14.add(ingredients.get(13));
        Chocolate c14 = new Chocolate("Nesquik jagoda", ingredients14,"Nestle" , 174.99f , 100.00 , 55.00 , 2 , 542.00);
        chocolates.add(c14);

        List<String> ingredients15 = new ArrayList<>();
        ingredients15.add(ingredients.get(3));
        ingredients15.add(ingredients.get(6));
        Chocolate c15 = new Chocolate("Bombone kikiriki", ingredients15,"M&M" , 94.99f , 45.00 , 24.08 , 3 , 230.40);
        chocolates.add(c15);


        List<String> ingredients16 = new ArrayList<>();
        ingredients16.add(ingredients.get(0));
        ingredients16.add(ingredients.get(6));
        Chocolate c16 = new Chocolate("Bombone čokolada", ingredients16,"M&M" , 94.99f , 45.00 , 30.28 , 2 , 216.00);
        chocolates.add(c16);

        List<String> ingredients17 = new ArrayList<>();
        ingredients17.add(ingredients.get(6));
        ingredients17.add(ingredients.get(14));
        Chocolate c17 = new Chocolate("Crunch", ingredients17,"Nestle" , 206.99f , 100.00 , 51.40 , 2 , 513.00);
        chocolates.add(c17);


        List<String> ingredients18 = new ArrayList<>();
        ingredients18.add(ingredients.get(5));
        ingredients18.add(ingredients.get(6));
        ingredients18.add(ingredients.get(15));
        Chocolate c18 = new Chocolate("Toblerone", ingredients18,"Toblerone" , 262.99f , 100.00 , 59.50 , 2 , 535.00);
        chocolates.add(c18);








/*
        List<String> ingredients1= new ArrayList<>();
        ingredients1.add(ingredients.get(2));
        ingredients1.add(ingredients.get(3));

        Chocolate c1 = new Chocolate("Mars", ingredients1,"Mars" , 50);
        chocolates.add(c1);

        List<String> ingredients2= new ArrayList<>();
        ingredients2.add(ingredients.get(2));
        ingredients2.add(ingredients.get(9));
        Chocolate c2 = new Chocolate("Bounty", ingredients2,"Mars" , 75,100,55,5);
        chocolates.add(c2);


        List<String> ingredients3= new ArrayList<>();
        ingredients3.add(ingredients.get(2));
        ingredients3.add(ingredients.get(3));
        ingredients3.add(ingredients.get(4));

        Chocolate c3 = new Chocolate("Twix", ingredients3,"Mars" , 75,100,46,1);
        chocolates.add(c3);


        List<String> ingrediants4= new ArrayList<>();
        ingrediants4.add(ingredients.get(2));
        ingrediants4.add(ingredients.get(10));



        Chocolate c4 = new Chocolate("Kidy sa mlekom", ingrediants4,"Pionir" , 33);
        //c4.setScore(95);
        chocolates.add(c4);


        List<String> ingredients5= new ArrayList<>();
        ingredients5.add(ingredients.get(2));
        ingredients5.add(ingredients.get(5));
        Chocolate c5 = new Chocolate("Kidy sa jagodom", ingredients5,"Pionir" , 33);
        //c5.setScore(100);
        chocolates.add(c5);



        List<String> ingredients6= new ArrayList<>();
        ingredients6.add(ingredients.get(2));
        ingredients6.add(ingredients.get(11));
        Chocolate c6 = new Chocolate("Kidy sa lesnikom", ingredients6,"Pionir" , 33);
        //c6.setScore(100);
        chocolates.add(c6);


        List<String> ingredients7= new ArrayList<>();
        ingredients7.add(ingredients.get(2));
        ingredients7.add(ingredients.get(11));
        Chocolate c7 = new Chocolate("Kidy sa lesnikom2", ingredients7,"Pionir" , 33);
        //c6.setScore(100);
        chocolates.add(c7);



        List<String> ingredients8= new ArrayList<>();
        ingredients8.add(ingredients.get(1));
 //       ingredients8.add(ingredients.get(12));

        Chocolate c8 = new Chocolate("Milka bela", ingredients8,"Milka" , 120,100,30,2);
        chocolates.add(c8);

        List<String> ingredients9= new ArrayList<>();
        ingredients9.add(ingredients.get(0));

        Chocolate c9 = new Chocolate("Banat crna cokolada", ingredients9,"Swisslion-Takovo" , 100,80,20,3, 500);
        chocolates.add(c9);

        List<String> ingredients10= new ArrayList<>();
        ingredients10.add(ingredients.get(2));
        ingredients10.add(ingredients.get(4));

        Chocolate c10 = new Chocolate("Banat mlecna cokolada sa keksom", ingredients10,"Swisslion-Takovo" , 100,80,60,2, 565);
        chocolates.add(c10);



        List<String> ingredients11= new ArrayList<>();
        ingredients11.add(ingredients.get(2));
        ingredients11.add(ingredients.get(5));

        Chocolate c11 = new Chocolate("Banat mlecna cokolada sa jagodom", ingredients11,"Swisslion-Takovo" , 100,80,60,2, 565);
        chocolates.add(c11);


        List<String> ingredients12= new ArrayList<>();
        ingredients12.add(ingredients.get(2));
        ingredients12.add(ingredients.get(10));

        Chocolate c12 = new Chocolate("Banat mlecna  sa vise mleka", ingredients12,"Swisslion-Takovo" , 100,80,60,2, 536);
        chocolates.add(c12);

        List<String> ingredients13= new ArrayList<>();
        ingredients13.add(ingredients.get(2));

        Chocolate c13 = new Chocolate("Dorina mlecna cokolada bez secera", ingredients13,"Kras" , 150,80,0,2, 336);
        chocolates.add(c13);


        List<String> ingredients14= new ArrayList<>();
        ingredients14.add(ingredients.get(2));
        ingredients14.add(ingredients.get(4));

        Chocolate c14 = new Chocolate("Dorina mlecna cokolada sa keksom", ingredients14,"Kras" , 100,80,60,2, 575);
        chocolates.add(c14);


        List<String> ingredients15= new ArrayList<>();
        ingredients15.add(ingredients.get(0));
        ingredients15.add(ingredients.get(13));

        Chocolate c15 = new Chocolate("Dorina ledena cokolada", ingredients15,"Kras" , 100,80,38,2, 475);
        chocolates.add(c15);


        List<String> ingredients16= new ArrayList<>();

        ingredients16.add(ingredients.get(10));

        ingredients16.add(ingredients.get(2));

        Chocolate c16 = new Chocolate("Kinder mlecna cokolada", ingredients16,"Kinder" , 160,100,60,4, 586);
        chocolates.add(c16);


*/
    }

    public void addChocolate(Chocolate chocolate){
        this.chocolates.add(chocolate);

    }


    public String addIngredient(String ingredient){
        if(! this.ingredients.contains(ingredient)) {
            this.ingredients.add(ingredient);
            return ingredient;
        }else{
            return "The ingredient already exists.";
        }

    }










}
