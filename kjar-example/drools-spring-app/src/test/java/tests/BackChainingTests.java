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
        c.setGrade(1);

        kieSession.insert(c);
        kieSession.insert(new ChocolateGrade("none@gmail.com","Twix",1));


        kieSession.getAgenda().getAgendaGroup("recommend-registered").setFocus();

        int firedRules = kieSession.fireAllRules();
       // assertThat(1, equalTo(firedRules));
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
        c1.setGrade(1);
        kieSession.insert(c1);


        List<String> ingrediants2= new ArrayList<>();
        ingrediants1.add("bela cokolada");
        Chocolate c2 = new Chocolate("Twix", ingrediants2,"Mars" , 1000,30,1000,1);

        c2.setGrade(1);
        kieSession.insert(c2);



        List<String> ingrediants3= new ArrayList<>();
        ingrediants3.add("karamela");
        ingrediants3.add("crna cokolada");

        Chocolate c3 = new Chocolate("Mars", ingrediants3,"Mars" , 1000,30,1000,1);
        c3.setGrade(1);
        kieSession.insert(c3);




        kieSession.insert(new ChocolateGrade("none@gmail.com","Twix",1));
        kieSession.insert(new ChocolateGrade("none@gmail.com","Snikers",1));
        kieSession.insert(new ChocolateGrade("none@gmail.com","Mars",1));


        kieSession.getAgenda().getAgendaGroup("recommend-registered").setFocus();

        int firedRules = kieSession.fireAllRules();
      //  assertThat(1, equalTo(firedRules));
        assertEquals(50, c2.getScore());
        assertEquals(0, c1.getScore());
        assertEquals(0, c3.getScore());



        kieSession.dispose();


    }


    @Test
    public void testSimilarGrading() {
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


        User user = new User("none@gmail.com","none","none", "none" , "566566" , UserType.REGISTERED_USER , UserRank.NONE);
        kieSession.insert(user);

        kieSession.insert(new ChocolateGrade("silver@gmail.com","Snikers",5));
        kieSession.insert(new ChocolateGrade("none@gmail.com","Snikers",5));
        kieSession.insert(new ChocolateGrade("silver@gmail.com","Mars",5));

        kieSession.getAgenda().getAgendaGroup("backward-chaining").setFocus();

        int firedRules = kieSession.fireAllRules();
       // assertThat(1, equalTo(firedRules));
        assertEquals(0, c1.getScore());
        assertEquals(118, c3.getScore());

        kieSession.dispose();


    }


    @Test
    public void testSimilarGrading2() {
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


        User user = new User("none@gmail.com","none","none", "none" , "566566" , UserType.REGISTERED_USER , UserRank.NONE);
        kieSession.insert(user);

        kieSession.insert(new ChocolateGrade("silver@gmail.com","Snikers",1));
        kieSession.insert(new ChocolateGrade("none@gmail.com","Snikers",5));
        kieSession.insert(new ChocolateGrade("silver@gmail.com","Mars",5));

        kieSession.getAgenda().getAgendaGroup("backward-chaining").setFocus();

        int firedRules = kieSession.fireAllRules();
        assertThat(0, equalTo(firedRules));
        assertEquals(0, c1.getScore());
        assertEquals(0, c3.getScore());

        kieSession.dispose();


    }


    @Test
    public void testSimilarGrading3() {
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


        User user = new User("none@gmail.com","none","none", "none" , "566566" , UserType.REGISTERED_USER , UserRank.NONE);
        kieSession.insert(user);

        kieSession.insert(new ChocolateGrade("platinum@gmail.com","Snikers",5));
        kieSession.insert(new ChocolateGrade("none@gmail.com","Snikers",5));
        kieSession.insert(new ChocolateGrade("silver@gmail.com","Mars",5));

        kieSession.getAgenda().getAgendaGroup("backward-chaining").setFocus();

        int firedRules = kieSession.fireAllRules();
        assertThat(0, equalTo(firedRules));
        assertEquals(0, c1.getScore());
        assertEquals(0, c3.getScore());

        kieSession.dispose();


    }


    @Test
    public void testSimilarGrading4() {
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


        User user = new User("none@gmail.com","none","none", "none" , "566566" , UserType.REGISTERED_USER , UserRank.NONE);
        kieSession.insert(user);

        kieSession.insert(new ChocolateGrade("silver@gmail.com","Snikers",5));
        kieSession.insert(new ChocolateGrade("none@gmail.com","Snikers",5));
        kieSession.insert(new ChocolateGrade("silver@gmail.com","Mars",1));

        kieSession.getAgenda().getAgendaGroup("backward-chaining").setFocus();

        int firedRules = kieSession.fireAllRules();
        assertThat(0, equalTo(firedRules));
        assertEquals(0, c1.getScore());
        assertEquals(0, c3.getScore());

        kieSession.dispose();


    }



    @Test
    public void testSimilarGrading5() {
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


        User user = new User("none@gmail.com","none","none", "none" , "566566" , UserType.REGISTERED_USER , UserRank.NONE);
        kieSession.insert(user);

        kieSession.insert(new ChocolateGrade("silver@gmail.com","Snikers",5));
        kieSession.insert(new ChocolateGrade("none@gmail.com","Snikers",5));
        kieSession.insert(new ChocolateGrade("none@gmail.com","Mars",5));
        kieSession.insert(new ChocolateGrade("silver@gmail.com","Mars",5));

        kieSession.getAgenda().getAgendaGroup("backward-chaining").setFocus();

        int firedRules = kieSession.fireAllRules();
        assertThat(0, equalTo(firedRules));
        assertEquals(0, c1.getScore());
        assertEquals(0, c3.getScore());

        kieSession.dispose();


    }




    @Test
    public void testFarSimilarGrading() {
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

        List<String> ingrediants4= new ArrayList<>();
        ingrediants4.add("kokos");
        ingrediants4.add("mlecna cokolada");

        Chocolate c4 = new Chocolate("Bounty", ingrediants4,"Mars" , 1000,30,1000,1);

        kieSession.insert(c4);


        User user = new User("none@gmail.com","none","none", "none" , "566566" , UserType.REGISTERED_USER , UserRank.NONE);
        kieSession.insert(user);

        kieSession.insert(new ChocolateGrade("none@gmail.com","Snikers",5));
        kieSession.insert(new ChocolateGrade("silver@gmail.com","Snikers",5));
        kieSession.insert(new ChocolateGrade("silver@gmail.com","Mars",5));
        kieSession.insert(new ChocolateGrade("gold@gmail.com","Bounty",5));
        kieSession.insert(new ChocolateGrade("gold@gmail.com","Mars",5));




        kieSession.getAgenda().getAgendaGroup("backward-chaining").setFocus();

        int firedRules = kieSession.fireAllRules();

        assertEquals(118, c3.getScore());
        assertEquals(75, c4.getScore());


        kieSession.dispose();


    }



}
