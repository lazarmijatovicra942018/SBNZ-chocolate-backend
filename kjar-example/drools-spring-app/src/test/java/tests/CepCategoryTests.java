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


public class CepCategoryTests {

    @Test
    public void testNoneToBronze() {

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

        kieSession.insert(new ChocolatePurchase("none@gmail.com","Bounty",50,15));
        kieSession.insert(new ChocolatePurchase("none@gmail.com","Twix",50,15));

       // kieClock.advanceTime(61, TimeUnit.MINUTES);

        kieSession.getAgenda().getAgendaGroup("cep-category").setFocus();


        int firedRules = kieSession.fireAllRules();
        assertThat(1, equalTo(firedRules));
        assertEquals(UserRank.BRONZE, user.getUserRank());

        kieSession.dispose();

    }


    @Test
    public void testNoneToBronzeFailNotEnough() {

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

        kieSession.insert(new ChocolatePurchase("none@gmail.com","Bounty",20,15));
        kieSession.insert(new ChocolatePurchase("none@gmail.com","Twix",20,15));

        // kieClock.advanceTime(61, TimeUnit.MINUTES);

        kieSession.getAgenda().getAgendaGroup("cep-category").setFocus();


        int firedRules = kieSession.fireAllRules();
        assertThat(0, equalTo(firedRules));
        assertEquals(UserRank.NONE, user.getUserRank());

        kieSession.dispose();

    }

    @Test
    public void testNoneToBronzeFail() {

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

        kieSession.insert(new ChocolatePurchase("none@gmail.com","Bounty",50,15));

        kieClock.advanceTime(750, TimeUnit.HOURS);
        kieSession.insert(new ChocolatePurchase("none@gmail.com","Twix",50,15));

        // kieClock.advanceTime(61, TimeUnit.MINUTES);

        kieSession.getAgenda().getAgendaGroup("cep-category").setFocus();


        int firedRules = kieSession.fireAllRules();
        assertThat(0, equalTo(firedRules));
        assertEquals(UserRank.NONE, user.getUserRank());

        kieSession.dispose();

    }




    @Test
    public void testBronzeToSilver() {

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

        User user = new User("none@gmail.com","none","none", "none" , "566566" , UserType.REGISTERED_USER , UserRank.BRONZE);
        kieSession.insert(user);

        kieSession.insert(new ChocolatePurchase("none@gmail.com","Bounty",50,30));
        kieSession.insert(new ChocolatePurchase("none@gmail.com","Twix",50,30));

        // kieClock.advanceTime(61, TimeUnit.MINUTES);

        kieSession.getAgenda().getAgendaGroup("cep-category").setFocus();


        int firedRules = kieSession.fireAllRules();
        assertThat(1, equalTo(firedRules));
        assertEquals(UserRank.SILVER, user.getUserRank());

        kieSession.dispose();

    }


    @Test
    public void testBronzeToSilverFailNotEnough() {

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

        User user = new User("none@gmail.com","none","none", "none" , "566566" , UserType.REGISTERED_USER , UserRank.BRONZE);
        kieSession.insert(user);

        kieSession.insert(new ChocolatePurchase("none@gmail.com","Bounty",20,15));
        kieSession.insert(new ChocolatePurchase("none@gmail.com","Twix",20,15));

        // kieClock.advanceTime(61, TimeUnit.MINUTES);

        kieSession.getAgenda().getAgendaGroup("cep-category").setFocus();


        int firedRules = kieSession.fireAllRules();
        assertThat(0, equalTo(firedRules));
        assertEquals(UserRank.BRONZE, user.getUserRank());

        kieSession.dispose();

    }

    @Test
    public void testBronzeToSilverFail() {

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

        User user = new User("none@gmail.com","none","none", "none" , "566566" , UserType.REGISTERED_USER , UserRank.BRONZE);
        kieSession.insert(user);

        kieSession.insert(new ChocolatePurchase("none@gmail.com","Bounty",50,30));

        kieClock.advanceTime(750, TimeUnit.HOURS);
        kieSession.insert(new ChocolatePurchase("none@gmail.com","Twix",50,30));

        // kieClock.advanceTime(61, TimeUnit.MINUTES);

        kieSession.getAgenda().getAgendaGroup("cep-category").setFocus();


        int firedRules = kieSession.fireAllRules();
        assertThat(0, equalTo(firedRules));
        assertEquals(UserRank.BRONZE, user.getUserRank());
        kieSession.dispose();


    }


    @Test
    public void testSilverToGold() {

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

        User user = new User("none@gmail.com","none","none", "none" , "566566" , UserType.REGISTERED_USER , UserRank.SILVER);
        kieSession.insert(user);

        kieSession.insert(new ChocolatePurchase("none@gmail.com","Bounty",50,60));
        kieSession.insert(new ChocolatePurchase("none@gmail.com","Twix",50,60));

        // kieClock.advanceTime(61, TimeUnit.MINUTES);

        kieSession.getAgenda().getAgendaGroup("cep-category").setFocus();


        int firedRules = kieSession.fireAllRules();
        assertThat(1, equalTo(firedRules));
        assertEquals(UserRank.GOLD, user.getUserRank());

        kieSession.dispose();

    }


    @Test
    public void testSilverToGoldFailNotEnough() {

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

        User user = new User("none@gmail.com","none","none", "none" , "566566" , UserType.REGISTERED_USER , UserRank.SILVER);
        kieSession.insert(user);

        kieSession.insert(new ChocolatePurchase("none@gmail.com","Bounty",20,15));
        kieSession.insert(new ChocolatePurchase("none@gmail.com","Twix",20,15));

        // kieClock.advanceTime(61, TimeUnit.MINUTES);

        kieSession.getAgenda().getAgendaGroup("cep-category").setFocus();


        int firedRules = kieSession.fireAllRules();
        assertThat(0, equalTo(firedRules));
        assertEquals(UserRank.SILVER, user.getUserRank());

        kieSession.dispose();

    }

    @Test
    public void testSilverToGoldFail() {

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

        User user = new User("none@gmail.com","none","none", "none" , "566566" , UserType.REGISTERED_USER , UserRank.SILVER);
        kieSession.insert(user);

        kieSession.insert(new ChocolatePurchase("none@gmail.com","Bounty",50,45));

        kieClock.advanceTime(750, TimeUnit.HOURS);
        kieSession.insert(new ChocolatePurchase("none@gmail.com","Twix",50,45));

        // kieClock.advanceTime(61, TimeUnit.MINUTES);

        kieSession.getAgenda().getAgendaGroup("cep-category").setFocus();


        int firedRules = kieSession.fireAllRules();
        assertThat(0, equalTo(firedRules));
        assertEquals(UserRank.SILVER, user.getUserRank());
        kieSession.dispose();


    }

    @Test
    public void testGoldToPlatinum() {

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

        User user = new User("none@gmail.com","none","none", "none" , "566566" , UserType.REGISTERED_USER , UserRank.GOLD);
        kieSession.insert(user);

        kieSession.insert(new ChocolatePurchase("none@gmail.com","Bounty",50,60));
        kieSession.insert(new ChocolatePurchase("none@gmail.com","Twix",50,60));

        // kieClock.advanceTime(61, TimeUnit.MINUTES);

        kieSession.getAgenda().getAgendaGroup("cep-category").setFocus();


        int firedRules = kieSession.fireAllRules();
        assertThat(1, equalTo(firedRules));
        assertEquals(UserRank.PLATINUM, user.getUserRank());

        kieSession.dispose();

    }


    @Test
    public void testGoldToPlatinumFailNotEnough() {

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

        User user = new User("none@gmail.com","none","none", "none" , "566566" , UserType.REGISTERED_USER , UserRank.GOLD);
        kieSession.insert(user);

        kieSession.insert(new ChocolatePurchase("none@gmail.com","Bounty",20,15));
        kieSession.insert(new ChocolatePurchase("none@gmail.com","Twix",20,15));

        // kieClock.advanceTime(61, TimeUnit.MINUTES);

        kieSession.getAgenda().getAgendaGroup("cep-category").setFocus();


        int firedRules = kieSession.fireAllRules();
        assertThat(0, equalTo(firedRules));
        assertEquals(UserRank.GOLD, user.getUserRank());

        kieSession.dispose();

    }

    @Test
    public void testGoldToPlatinumFail() {

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

        User user = new User("none@gmail.com","none","none", "none" , "566566" , UserType.REGISTERED_USER , UserRank.GOLD);
        kieSession.insert(user);

        kieSession.insert(new ChocolatePurchase("none@gmail.com","Bounty",50,60));

        kieClock.advanceTime(750, TimeUnit.HOURS);
        kieSession.insert(new ChocolatePurchase("none@gmail.com","Twix",50,60));

        // kieClock.advanceTime(61, TimeUnit.MINUTES);

        kieSession.getAgenda().getAgendaGroup("cep-category").setFocus();


        int firedRules = kieSession.fireAllRules();
        assertThat(0, equalTo(firedRules));
        assertEquals(UserRank.GOLD, user.getUserRank());

        kieSession.dispose();

    }







}
