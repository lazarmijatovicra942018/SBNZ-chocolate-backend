package tests;

import demo.facts.*;
import org.drools.core.ClockType;
import org.drools.core.time.SessionPseudoClock;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;


import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CepSpamerTests {


    @Test
    public void testSpamer() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks
                .newKieContainer(ks.newReleaseId("sbnz.integracija", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
        KieBaseConfiguration kconf = ks.newKieBaseConfiguration();
        kconf.setOption(EventProcessingOption.STREAM);
        KieBase kieBase = kContainer.newKieBase(kconf);
        KieSessionConfiguration kconfig1 = ks.newKieSessionConfiguration();
        kconfig1.setOption(ClockTypeOption.get(ClockType.REALTIME_CLOCK.getId()));
        KieSession kieSession = kieBase.newKieSession(kconfig1, null);
        User user = new User("none@gmail.com","none","none", "none" , "566566" , UserType.REGISTERED_USER , UserRank.NONE);
        kieSession.insert(user);



        kieSession.insert(new ChocolateGrade(user.getEmail(),"Mars",1));
        kieSession.insert(new ChocolateGrade(user.getEmail(),"Twix",1));
        kieSession.insert(new ChocolateGrade(user.getEmail(),"Bounty",1));
        kieSession.insert(new ChocolateGrade(user.getEmail(),"Kidy sa jagodom",1));
        kieSession.insert(new ChocolateGrade(user.getEmail(),"Kidy sa mlekom",1));


        kieSession.getAgenda().getAgendaGroup("cep-spamer").setFocus();

        int firedRules = kieSession.fireAllRules();
        assertThat(1, equalTo(firedRules));
        assertEquals(true, user.getIsSpam());
        kieSession.dispose();


    }


    @Test
    public void testSpamerFourBadGrades() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks
                .newKieContainer(ks.newReleaseId("sbnz.integracija", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
        KieBaseConfiguration kconf = ks.newKieBaseConfiguration();
        kconf.setOption(EventProcessingOption.STREAM);
        KieBase kieBase = kContainer.newKieBase(kconf);
        KieSessionConfiguration kconfig1 = ks.newKieSessionConfiguration();
        kconfig1.setOption(ClockTypeOption.get(ClockType.REALTIME_CLOCK.getId()));
        KieSession kieSession = kieBase.newKieSession(kconfig1, null);
        User user = new User("none@gmail.com","none","none", "none" , "566566" , UserType.REGISTERED_USER , UserRank.NONE);
        kieSession.insert(user);



        kieSession.insert(new ChocolateGrade(user.getEmail(),"Mars",1));
        kieSession.insert(new ChocolateGrade(user.getEmail(),"Twix",1));
        kieSession.insert(new ChocolateGrade(user.getEmail(),"Bounty",1));
        kieSession.insert(new ChocolateGrade(user.getEmail(),"Kidy sa jagodom",1));
     //   kieSession.insert(new ChocolateGrade(user.getEmail(),"Kidy sa mlekom",1));


        kieSession.getAgenda().getAgendaGroup("cep-spamer").setFocus();

        int firedRules = kieSession.fireAllRules();
        assertThat(0, equalTo(firedRules));
        assertEquals(false, user.getIsSpam());
        kieSession.dispose();
    }


    @Test
    public void testSpamerFourBadGradesOneNeutral() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks
                .newKieContainer(ks.newReleaseId("sbnz.integracija", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
        KieBaseConfiguration kconf = ks.newKieBaseConfiguration();
        kconf.setOption(EventProcessingOption.STREAM);
        KieBase kieBase = kContainer.newKieBase(kconf);
        KieSessionConfiguration kconfig1 = ks.newKieSessionConfiguration();
        kconfig1.setOption(ClockTypeOption.get(ClockType.REALTIME_CLOCK.getId()));
        KieSession kieSession = kieBase.newKieSession(kconfig1, null);
        User user = new User("none@gmail.com","none","none", "none" , "566566" , UserType.REGISTERED_USER , UserRank.NONE);
        kieSession.insert(user);



        kieSession.insert(new ChocolateGrade(user.getEmail(),"Mars",1));
        kieSession.insert(new ChocolateGrade(user.getEmail(),"Twix",1));
        kieSession.insert(new ChocolateGrade(user.getEmail(),"Bounty",1));
        kieSession.insert(new ChocolateGrade(user.getEmail(),"Kidy sa jagodom",1));
        kieSession.insert(new ChocolateGrade(user.getEmail(),"Kidy sa mlekom",3));


        kieSession.getAgenda().getAgendaGroup("cep-spamer").setFocus();

        int firedRules = kieSession.fireAllRules();
        assertThat(0, equalTo(firedRules));
        assertEquals(false, user.getIsSpam());
        kieSession.dispose();
    }

    @Test
    public void testSpamerFiveBadGradesinMoreThanThreeMinutes() {

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks
                .newKieContainer(ks.newReleaseId("sbnz.integracija", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
        KieBaseConfiguration kconf = ks.newKieBaseConfiguration();
        kconf.setOption(EventProcessingOption.STREAM);
        KieBase kieBase = kContainer.newKieBase(kconf);
        KieSessionConfiguration kconfig1 = ks.newKieSessionConfiguration();
        kconfig1.setOption(ClockTypeOption.get(ClockType.PSEUDO_CLOCK.getId()));
        KieSession kieSession = kieBase.newKieSession(kconfig1, null);
        SessionPseudoClock kieClock = kieSession.getSessionClock();

        User user = new User("none@gmail.com","none","none", "none" , "566566" , UserType.REGISTERED_USER , UserRank.NONE);
        kieSession.insert(user);



        kieSession.insert(new ChocolateGrade(user.getEmail(),"Mars",1));
        kieSession.insert(new ChocolateGrade(user.getEmail(),"Twix",1));
        kieSession.insert(new ChocolateGrade(user.getEmail(),"Bounty",1));
        kieSession.insert(new ChocolateGrade(user.getEmail(),"Kidy sa jagodom",1));
        kieClock.advanceTime(4, TimeUnit.MINUTES);
        kieSession.insert(new ChocolateGrade(user.getEmail(),"Kidy sa mlekom",1));


        kieSession.getAgenda().getAgendaGroup("cep-spamer").setFocus();



        int firedRules = kieSession.fireAllRules();
        assertThat(0, equalTo(firedRules));
        assertEquals(false, user.getIsSpam());

        kieSession.dispose();

    }

    @Test
    public void testSpamerFiveBadGradesinLessThanThreeMinutes() {

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks
                .newKieContainer(ks.newReleaseId("sbnz.integracija", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
        KieBaseConfiguration kconf = ks.newKieBaseConfiguration();
        kconf.setOption(EventProcessingOption.STREAM);
        KieBase kieBase = kContainer.newKieBase(kconf);
        KieSessionConfiguration kconfig1 = ks.newKieSessionConfiguration();
        kconfig1.setOption(ClockTypeOption.get(ClockType.PSEUDO_CLOCK.getId()));
        KieSession kieSession = kieBase.newKieSession(kconfig1, null);
        SessionPseudoClock kieClock = kieSession.getSessionClock();

        User user = new User("none@gmail.com","none","none", "none" , "566566" , UserType.REGISTERED_USER , UserRank.NONE);
        kieSession.insert(user);



        kieSession.insert(new ChocolateGrade(user.getEmail(),"Mars",1));
        kieSession.insert(new ChocolateGrade(user.getEmail(),"Twix",1));
        kieSession.insert(new ChocolateGrade(user.getEmail(),"Bounty",1));
        kieSession.insert(new ChocolateGrade(user.getEmail(),"Kidy sa jagodom",1));
        kieClock.advanceTime(2, TimeUnit.MINUTES);
        kieSession.insert(new ChocolateGrade(user.getEmail(),"Kidy sa mlekom",1));


        kieSession.getAgenda().getAgendaGroup("cep-spamer").setFocus();



        int firedRules = kieSession.fireAllRules();
        assertThat(1, equalTo(firedRules));
        assertEquals(true, user.getIsSpam());

        kieSession.dispose();

    }

}
