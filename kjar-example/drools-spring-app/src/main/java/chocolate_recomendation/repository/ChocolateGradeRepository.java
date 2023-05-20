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
        chocolateGrades.add(cg1);

        ChocolateGrade cg2 = new ChocolateGrade("none@gmail.com","Bounty",1);
        chocolateGrades.add(cg2);


        ChocolateGrade cg3 = new ChocolateGrade("gold@gmail.com","Mars",1);
        chocolateGrades.add(cg3);

    }


}
