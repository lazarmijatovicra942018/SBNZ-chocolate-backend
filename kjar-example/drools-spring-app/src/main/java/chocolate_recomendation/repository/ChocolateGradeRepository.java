package chocolate_recomendation.repository;

import demo.facts.ChocolateGrade;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ChocolateGradeRepository {

    private static ChocolateGradeRepository instance = new ChocolateGradeRepository();

    private static List<ChocolateGrade> chocolateGrades ;

    public ChocolateGradeRepository() {
        this.initChocolateGrade();
    }

    public static void setInstance(ChocolateGradeRepository instance) {
        ChocolateGradeRepository.instance = instance;
    }

    public static ChocolateGradeRepository getInstance() {
        return instance;
    }

    public static List<ChocolateGrade> getChocolateGrades() {
        return chocolateGrades;
    }

    public static void setChocolateGrades(List<ChocolateGrade> chocolateGrades) {
        ChocolateGradeRepository.chocolateGrades = chocolateGrades;
    }

    public void initChocolateGrade(){
        chocolateGrades = new ArrayList<>();
        ChocolateGrade cg1 = new ChocolateGrade("none@gmail.com","Mars",5);
       // chocolateGrades.add(cg1);

        ChocolateGrade cg2 = new ChocolateGrade("none@gmail.com","Bounty",5);
    //    chocolateGrades.add(cg2);


        ChocolateGrade cg3 = new ChocolateGrade("none@gmail.com","Twix",1);
      //  chocolateGrades.add(cg3);



        ChocolateGrade cg4 = new ChocolateGrade("silver@gmail.com","Mars",5);
       // chocolateGrades.add(cg4);

        ChocolateGrade cg5 = new ChocolateGrade("platinum@gmail.com","Mars",5);
       // chocolateGrades.add(cg5);

        ChocolateGrade cg6 = new ChocolateGrade("none@gmail.com","Kidy sa jagodom",1);
        //chocolateGrades.add(cg6);

    }


}
