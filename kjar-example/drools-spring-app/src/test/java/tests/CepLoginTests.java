package tests;

import demo.facts.FailedLoginAttempt;
import demo.facts.User;
import demo.facts.UserRank;
import demo.facts.UserType;
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

public class CepLoginTests {

    @Test
    public void testFailedLoginAttempt() {
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
        kieSession.insert(new FailedLoginAttempt(user.getEmail()));
        kieSession.insert(new FailedLoginAttempt(user.getEmail()));
        kieSession.insert(new FailedLoginAttempt(user.getEmail()));

        kieSession.getAgenda().getAgendaGroup("cep-login").setFocus();

        int firedRules = kieSession.fireAllRules();
        assertThat(1, equalTo(firedRules));
        assertEquals(true, user.getIsBlocked());

        kieSession.dispose();


    }


    @Test
    public void testFailedLoginAttemptTwoAttempts() {
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
        kieSession.insert(new FailedLoginAttempt(user.getEmail()));
        kieSession.insert(new FailedLoginAttempt(user.getEmail()));

        kieSession.getAgenda().getAgendaGroup("cep-login").setFocus();

        int firedRules = kieSession.fireAllRules();
        assertThat(0, equalTo(firedRules));
        assertEquals(false, user.getIsBlocked());

        kieSession.dispose();


    }

    @Test
    public void testFailedLoginAttemtInMoreThanHour() {

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
        kieSession.insert(new FailedLoginAttempt(user.getEmail()));
        kieSession.insert(new FailedLoginAttempt(user.getEmail()));
        kieClock.advanceTime(61, TimeUnit.MINUTES);
        kieSession.insert(new FailedLoginAttempt(user.getEmail()));

        kieSession.getAgenda().getAgendaGroup("cep-login").setFocus();


        int firedRules = kieSession.fireAllRules();
        assertThat(0, equalTo(firedRules));
        assertEquals(false, user.getIsBlocked());

        kieSession.dispose();

    }


    @Test
    public void testFailedLoginAttemtInLessThanHour() {

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
        kieSession.insert(new FailedLoginAttempt(user.getEmail()));
        kieSession.insert(new FailedLoginAttempt(user.getEmail()));
        kieClock.advanceTime(59, TimeUnit.MINUTES);
        kieSession.insert(new FailedLoginAttempt(user.getEmail()));

        kieSession.getAgenda().getAgendaGroup("cep-login").setFocus();


        int firedRules = kieSession.fireAllRules();
        assertThat(1, equalTo(firedRules));
        assertEquals(true, user.getIsBlocked());

        kieSession.dispose();

    }



}
