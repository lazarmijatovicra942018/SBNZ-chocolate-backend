package chocolate_recomendation.service;

import chocolate_recomendation.repository.*;
import chocolate_recomendation.utils.MavenUtils;
import demo.facts.*;
import org.apache.maven.shared.invoker.MavenInvocationException;
import org.drools.core.ClockType;
import org.drools.template.ObjectDataCompiler;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.*;


@Service
public class ChocolateService {


    private final ChocolateRepository repository = ChocolateRepository.getInstance();

    private final StorageRepository storageRepository = new StorageRepository();


    private final ObjectDataCompiler objectDataCompiler;

    private final UserRepository userRepository = UserRepository.getInstance();

    private final ChocolateGradeRepository chocolateGradeRepository = ChocolateGradeRepository.getInstance();

    private final ChocolatePurchaseRepository chocolatePurchaseRepository = ChocolatePurchaseRepository.getInstance();

    private final KieContainer kieContainer;

    private static Logger log = LoggerFactory.getLogger(ChocolateService.class);


    @Autowired
    public ChocolateService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
        this.objectDataCompiler = new ObjectDataCompiler();
    }



    public KieSession GetKieSession(){
        KieServices ks = KieServices.Factory.get();

        KieBaseConfiguration kconf = ks.newKieBaseConfiguration();
        kconf.setOption(EventProcessingOption.STREAM);
        KieBase kieBase = kieContainer.newKieBase(kconf);
        KieSessionConfiguration kconfig1 = ks.newKieSessionConfiguration();
        return kieBase.newKieSession(kconfig1, null);

    }



    public List<Chocolate> getAll(){

        return repository.getChocolates();
    }


    public Chocolate getOneByName(String chocolateName){
        Chocolate chocolate = repository.getChocolates().stream().filter(c->c.getName().equals(chocolateName)).findFirst().orElse(null);
        KieSession kieSession = GetKieSession();
        kieSession.insert(chocolate);
        List<ChocolateGrade> chocolateGrades = chocolateGradeRepository.getChocolateGrades();
        for(ChocolateGrade cg : chocolateGrades){
            kieSession.insert(cg);
        }
        kieSession.getAgenda().getAgendaGroup("cep-login").setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();


        return chocolate;
    }


    public Chocolate getOneByNameWithDiscount(String chocolateName,int amount){
        Chocolate chocolate = new Chocolate(repository.getChocolates().stream().filter(c->c.getName().equals(chocolateName)).findFirst().orElse(null));
        chocolate.setAmmount(amount);
        KieSession kieSession = GetKieSession();
        kieSession.insert(chocolate);
        List<ChocolateGrade> chocolateGrades = chocolateGradeRepository.getChocolateGrades();
        for(ChocolateGrade cg : chocolateGrades){
            kieSession.insert(cg);
        }

        User loggedUser = userRepository.getLoggedUser();

        kieSession.insert(userRepository.getLoggedUser());
        kieSession.getAgenda().getAgendaGroup("discount").setFocus();
        kieSession.fireAllRules();

        kieSession.getAgenda().getAgendaGroup("grading").setFocus();
        kieSession.fireAllRules();
        kieSession.getAgenda().getAgendaGroup("my-grading").setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();


        return chocolate;
    }





    public List<String> getAllIngredients(){
        return repository.getIngredients();
    }







    public List<Chocolate> getDiscountedChocolateWithAmmount(int ammount){

        KieSession kieSession = GetKieSession();
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


        List<ChocolatePurchase> chocolatePurchase = chocolatePurchaseRepository.getChocolatePurchases();

        for(ChocolatePurchase cp : chocolatePurchase){
            kieSession.insert(cp);
        }

        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.MONTH, -6);


        Date sixMonthsAgo = calendar.getTime();

        kieSession.setGlobal("sixMonthsAgo", sixMonthsAgo);

        User loggedUser = userRepository.getLoggedUser();

        kieSession.insert(loggedUser);


    //    kieSession.setGlobal("registeredUser",loggedUser );

        kieSession.getAgenda().getAgendaGroup("discount").setFocus();
        kieSession.fireAllRules();

        kieSession.getAgenda().getAgendaGroup("grading").setFocus();
        kieSession.fireAllRules();
        kieSession.getAgenda().getAgendaGroup("my-grading").setFocus();
        kieSession.fireAllRules();

        kieSession.getAgenda().getAgendaGroup("recommend-registered").setFocus();
        kieSession.fireAllRules();

        List<User> users = userRepository.getUsers();



        kieSession.getAgenda().getAgendaGroup("backward-chaining").setFocus();
        kieSession.fireAllRules();

        kieSession.dispose();
        chocolates.sort(Comparator.comparing(Chocolate::getScore).reversed());
        return chocolates;

    }


    public List<Chocolate> getChocolatesForUnregisteredUsers(){

        KieSession kieSession = GetKieSession();
        List<Chocolate> original = repository.getChocolates();
        List<Chocolate> chocolates = new ArrayList<>();

        for(Chocolate c : original){
            Chocolate cl = new Chocolate(c);
            chocolates.add(cl);
            kieSession.insert(cl);
        }

        List<ChocolateGrade> chocolateGrades = chocolateGradeRepository.getChocolateGrades();

        for(ChocolateGrade cg : chocolateGrades){
            kieSession.insert(cg);
        }


        List<ChocolatePurchase> chocolatePurchase = chocolatePurchaseRepository.getChocolatePurchases();

        for(ChocolatePurchase cp : chocolatePurchase){
            kieSession.insert(cp);
        }
        kieSession.getAgenda().getAgendaGroup("grading").setFocus();
        kieSession.fireAllRules();

        kieSession.getAgenda().getAgendaGroup("recommend-unrecognised").setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();

        chocolates.sort(Comparator.comparing(Chocolate::getScore).reversed());

        if(chocolates.size()<10){return chocolates;}
        return chocolates.subList(0,10);

    }


    public Object addOrUpdateChocolateGrade(String chocolateName , int grade){
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


    public Object addChocolatePurchase(String chocolateName , int amount){
        ChocolatePurchase chocolatePurchase= new ChocolatePurchase(userRepository.getLoggedUser().getEmail(), chocolateName , amount);
        return chocolatePurchaseRepository.addChocolatePurchase(chocolatePurchase);
    }


    public List<ChocolatePurchase> getChocolatePurchases(){
        return chocolatePurchaseRepository.getChocolatePurchases();
    }

    public Rule addChocolateDiscountRule(String chocolateName ,int discount) throws MavenInvocationException {
        Rule rule = new Rule(chocolateName,discount);

        this.storageRepository.save(rule.getId(), this.getRuleTemplate(rule));


        MavenUtils.mavenCleanAndInstallRules();

        return rule;



    }

    public String getRuleTemplate(Rule r){
    //    String filePath = "..\\kjar-example\\drools-spring-kjar\\src\\main\\resources\\rules\\templates\\GeneralTemplate.drt";
        String filePath = "C:\\Users\\lazar\\OneDrive\\Desktop\\fakultet\\SBNZ NOVI PROJEKAT\\SBNZ-chocolate-backend\\kjar-example\\drools-spring-kjar\\src\\main\\resources\\rules\\templates\\GeneralTemplate.drt";
        String rule = "";
        InputStream inputStream;
        try {
            // Create a FileInputStream to read the file
            File file = new File(filePath);
            inputStream = new FileInputStream(file);
            List<Rule> data = new ArrayList<>();
            data.add(r);
            rule =   this.objectDataCompiler.compile(data,inputStream);

             inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rule;

    }


}
