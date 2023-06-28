package tests;

import demo.facts.*;
import org.drools.core.ClockType;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import java.util.ArrayList;
import java.util.List;
public class BackChainingTests {

    @Test
    public void testUniqueIngredients() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks
                .newKieContainer(ks.newReleaseId("sbnz.integracija", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
        KieBaseConfiguration kconf = ks.newKieBaseConfiguration();
        kconf.setOption(EventProcessingOption.STREAM);
        KieBase kieBase = kContainer.newKieBase(kconf);
        KieSessionConfiguration kconfig1 = ks.newKieSessionConfiguration();
        kconfig1.setOption(ClockTypeOption.get(ClockType.REALTIME_CLOCK.getId()));
        KieSession kieSession = kieBase.newKieSession(kconfig1, null);


        List<String> ingrediants1= new ArrayList<>();
        ingrediants1.add("bela cokolada");
        Chocolate c = new Chocolate("Twix", ingrediants1,"Mars" , 1000,30,1000,1);

        kieSession.insert(c);
        kieSession.insert(new ChocolateGrade("none@gmail.com","Twix",1));

        kieSession.getAgenda().getAgendaGroup("recommend-registered").setFocus();

        int firedRules = kieSession.fireAllRules();
        assertThat(1, equalTo(firedRules));
        assertEquals(50, c.getScore());

        kieSession.dispose();


    }


    @Test
    public void testUniqueIngredientsMoreChocolates() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks
                .newKieContainer(ks.newReleaseId("sbnz.integracija", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
        KieBaseConfiguration kconf = ks.newKieBaseConfiguration();
        kconf.setOption(EventProcessingOption.STREAM);
        KieBase kieBase = kContainer.newKieBase(kconf);
        KieSessionConfiguration kconfig1 = ks.newKieSessionConfiguration();
        kconfig1.setOption(ClockTypeOption.get(ClockType.REALTIME_CLOCK.getId()));
        KieSession kieSession = kieBase.newKieSession(kconfig1, null);


        List<String> ingrediants1= new ArrayList<>();
        ingrediants1.add("karamela");
        ingrediants1.add("mlecna cokolada");

        Chocolate c1 = new Chocolate("Snikers", ingrediants1,"Mars" , 1000,30,1000,1);

        kieSession.insert(c1);


        List<String> ingrediants2= new ArrayList<>();
        ingrediants1.add("bela cokolada");
        Chocolate c2 = new Chocolate("Twix", ingrediants2,"Mars" , 1000,30,1000,1);

        kieSession.insert(c2);



        List<String> ingrediants3= new ArrayList<>();
        ingrediants3.add("karamela");
        ingrediants3.add("crna cokolada");

        Chocolate c3 = new Chocolate("Mars", ingrediants3,"Mars" , 1000,30,1000,1);

        kieSession.insert(c3);




        kieSession.insert(new ChocolateGrade("none@gmail.com","Twix",1));
        kieSession.insert(new ChocolateGrade("none@gmail.com","Snikers",1));
        kieSession.insert(new ChocolateGrade("none@gmail.com","Mars",1));


        kieSession.getAgenda().getAgendaGroup("recommend-registered").setFocus();

        int firedRules = kieSession.fireAllRules();
        assertThat(1, equalTo(firedRules));
        assertEquals(50, c2.getScore());
        assertEquals(0, c1.getScore());
        assertEquals(0, c3.getScore());



        kieSession.dispose();


    }


}
