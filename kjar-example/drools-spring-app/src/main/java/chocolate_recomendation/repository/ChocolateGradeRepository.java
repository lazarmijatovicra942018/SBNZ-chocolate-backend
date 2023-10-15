package chocolate_recomendation.repository;

import demo.facts.ChocolateGrade;
import org.springframework.stereotype.Repository;
import java.util.Random;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ChocolateGradeRepository {

    private static ChocolateGradeRepository instance = new ChocolateGradeRepository();

    private static List<ChocolateGrade> chocolateGrades ;

    public ChocolateGradeRepository() {
        this.initChocolateGrade();
    }

    public static ChocolateGradeRepository getInstance() {
        return instance;
    }

    public static List<ChocolateGrade> getChocolateGrades() {
        return chocolateGrades;
    }

    public void initChocolateGrade(){
        chocolateGrades = new ArrayList<>();
        List<String> chocolateNames = new ArrayList<>() ;
        chocolateNames.add("Cipiripi");
        chocolateNames.add("Snikers");
        chocolateNames.add("Milky Way");
        chocolateNames.add("Bounty");
        chocolateNames.add("Twix");
        chocolateNames.add("Mars");
        chocolateNames.add("Za-za");
        chocolateNames.add("Tamna čokolada");
        chocolateNames.add("Najlepše želje selection 75%");
        chocolateNames.add("Bela čokolada");
        chocolateNames.add("Lešnik");
        chocolateNames.add("Čokolada Kinder");
        chocolateNames.add("Jagoda");
        chocolateNames.add("Nesquik jagoda");
        chocolateNames.add("Bombone kikiriki");
        chocolateNames.add("Bombone čokolada");
        chocolateNames.add("Crunch");
        chocolateNames.add("Toblerone");

        List<String> userNames = new ArrayList<>() ;

        userNames.add("none@gmail.com");
        userNames.add("bronze@gmail.com");
        userNames.add("silver@gmail.com");
        userNames.add("gold@gmail.com");
        userNames.add("platinum@gmail.com");
        Random rand = new Random();

        for (String chocolateName : chocolateNames) {
            for (String userName : userNames) {
                int grade = rand.nextInt(6);
                if(grade != 0){
                    ChocolateGrade cg = new ChocolateGrade(userName,chocolateName,grade);
                    chocolateGrades.add(cg);
                }
            }
        }




        /*
        ChocolateGrade cg1 = new ChocolateGrade("none@gmail.com","Mars",5);
        chocolateGrades.add(cg1);

        ChocolateGrade cg2 = new ChocolateGrade("none@gmail.com","Bounty",5);
     //   chocolateGrades.add(cg2);
        chocolateGrades.add(cg2);


        ChocolateGrade cg3 = new ChocolateGrade("none@gmail.com","Twix",5);
      //  chocolateGrades.add(cg3);



        ChocolateGrade cg4 = new ChocolateGrade("silver@gmail.com","Mars",5);
        chocolateGrades.add(cg4);

        ChocolateGrade cg34 = new ChocolateGrade("silver@gmail.com","Twix",5);
       // chocolateGrades.add(cg34);


        ChocolateGrade cg51 = new ChocolateGrade("platinum@gmail.com","Twix",5);
        chocolateGrades.add(cg51);


        ChocolateGrade cg5 = new ChocolateGrade("platinum@gmail.com","Kidy sa jagodom",5);
        chocolateGrades.add(cg5);

        ChocolateGrade cg6 = new ChocolateGrade("none@gmail.com","Kidy sa jagodom",5);
    //    chocolateGrades.add(cg6);

        ChocolateGrade cg7 = new ChocolateGrade("silver@gmail.com","Kidy sa mlekom",5);
//        chocolateGrades.add(cg7);

        ChocolateGrade cg8 = new ChocolateGrade("silver@gmail.com","Kidy sa jagodom",5);
     //   chocolateGrades.add(cg8);


 */
    }




}
