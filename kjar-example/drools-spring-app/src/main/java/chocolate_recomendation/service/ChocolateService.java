package chocolate_recomendation.service;

import chocolate_recomendation.repository.ChocolateGradeRepository;
import chocolate_recomendation.repository.ChocolateRepository;
import chocolate_recomendation.repository.UserRepository;
import demo.facts.Chocolate;
import demo.facts.ChocolateGrade;
import demo.facts.User;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChocolateService {


    private final ChocolateRepository repository = ChocolateRepository.getInstance();


    private final UserRepository userRepository = UserRepository.getInstance();

    private final ChocolateGradeRepository chocolateGradeRepository = ChocolateGradeRepository.getInstance();
    private final KieContainer kieContainer;

    private static Logger log = LoggerFactory.getLogger(ChocolateService.class);


    @Autowired
    public ChocolateService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;

    }

    public List<Chocolate> getAll(){
        return repository.getChocolates();
    }

    public List<String> getAllIngredients(){
        return repository.getIngredients();
    }







    public List<Chocolate> getDiscountedChocolateWithAmmount(int ammount){

        KieSession kieSession = kieContainer.newKieSession();
        List<Chocolate> original = repository.getChocolates();
        List<Chocolate> chocolates = new ArrayList<>();

        for(Chocolate c : original){
            Chocolate cl = new Chocolate(c);
            cl.setAmmount(ammount);
            chocolates.add(cl);
            kieSession.insert(cl);
        }

        List<ChocolateGrade> chocolateGrades = chocolateGradeRepository.getChocolateGrades();

        for(ChocolateGrade cg : chocolateGrades){
            kieSession.insert(cg);
        }


        kieSession.insert(userRepository.getLoggedUser());
        kieSession.getAgenda().getAgendaGroup("discount").setFocus();
        kieSession.fireAllRules();

        kieSession.getAgenda().getAgendaGroup("grading").setFocus();
        kieSession.fireAllRules();


        kieSession.dispose();
        return chocolates;

    }


    public Object AddOrUpdateChocolateGrade(String chocolateName , int grade){
        ChocolateGrade chocolateGrade = new ChocolateGrade( userRepository.getLoggedUser().getEmail(), chocolateName ,grade);
        List<ChocolateGrade> chocolateGrades = chocolateGradeRepository.getChocolateGrades();
        ChocolateGrade cg =  chocolateGrades.stream().filter(c->c.getGradeId().equals(chocolateGrade.getGradeId())).findFirst().orElse(null);
        if(cg==null){
            chocolateGrades.add(chocolateGrade);
            return chocolateGrade;
        }else{
            cg.setGrade(grade);
            return cg;
        }
    }
}
