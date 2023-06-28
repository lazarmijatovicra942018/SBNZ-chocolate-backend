package demo.facts;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import java.io.Serializable;


@Role(Role.Type.EVENT)
@Expires("3h")
public class FailedLoginAttempt implements Serializable {

    private String userEmail;

    public FailedLoginAttempt(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
