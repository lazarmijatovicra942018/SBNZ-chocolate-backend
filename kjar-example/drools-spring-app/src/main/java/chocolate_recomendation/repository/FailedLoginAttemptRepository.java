package chocolate_recomendation.repository;

import demo.facts.FailedLoginAttempt;

import java.util.ArrayList;
import java.util.List;

public class FailedLoginAttemptRepository {

    private List<FailedLoginAttempt> failedLoginAttempts;

    private static FailedLoginAttemptRepository instance  = new FailedLoginAttemptRepository();

    public List<FailedLoginAttempt> getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    public void setFailedLoginAttempts(List<FailedLoginAttempt> failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
    }

    public static FailedLoginAttemptRepository getInstance() {
        return instance;
    }

    public static void setInstance(FailedLoginAttemptRepository instance) {
        FailedLoginAttemptRepository.instance = instance;
    }

    public FailedLoginAttemptRepository() {

        this.initFailedLoginAttempts();
    }

    public void initFailedLoginAttempts(){
        failedLoginAttempts = new ArrayList<>();
        FailedLoginAttempt fla1 = new FailedLoginAttempt("none@gmail.com");
      //  failedLoginAttempts.add(fla1);

        FailedLoginAttempt fla2 = new FailedLoginAttempt("none@gmail.com");
        //failedLoginAttempts.add(fla2);


        FailedLoginAttempt fla3 = new FailedLoginAttempt("none@gmail.com");
        //failedLoginAttempts.add(fla3);




    }

    public void addFailedLoginAttempt(FailedLoginAttempt failedLoginAttempt){
        this.failedLoginAttempts.add(failedLoginAttempt);
    }

}
