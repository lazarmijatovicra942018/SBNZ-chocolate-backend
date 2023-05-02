package chocolate_recomendation.service;

import chocolate_recomendation.repository.ChocolateRepository;
import chocolate_recomendation.repository.UserRepository;
import demo.facts.Chocolate;
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

    private final KieContainer kieContainer;

    private static Logger log = LoggerFactory.getLogger(ChocolateService.class);


    @Autowired
    public ChocolateService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;

    }

    public List<Chocolate> getAll(){
        return repository.getChocolates();
    }


    public List<Chocolate> getDiscountedChocolate(){

        KieSession kieSession = kieContainer.newKieSession();
        List<Chocolate> original = repository.getChocolates();
        List<Chocolate> chocolates = new ArrayList<>();

        for(Chocolate c : original){
            Chocolate cl = new Chocolate(c);
            chocolates.add(cl);
            kieSession.insert(cl);
        }
        kieSession.insert(userRepository.getLoggedUser());
        kieSession.getAgenda().getAgendaGroup("discount").setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();
        return chocolates;

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
        kieSession.insert(userRepository.getLoggedUser());
        kieSession.getAgenda().getAgendaGroup("discount").setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();
        return chocolates;

    }



}