package demo.facts;


import org.kie.api.definition.type.Position;
import org.kie.api.definition.type.Role;

import java.io.Serializable;

@Role(Role.Type.EVENT)
public class ChocolateGrade implements Serializable {
    private String  gradeId;

    @Position(0)
    private String userEmail ;

    @Position(1)
    private String chocolateName;

    @Position(2)
    private int grade;

    public ChocolateGrade(String userEmail, String chocolateName, int grade) {
        this.userEmail = userEmail;
        this.chocolateName = chocolateName;
        this.grade = grade;
        this.gradeId = generateId(userEmail,chocolateName);
    }

    public String generateId(String userEmail , String chocolateName){
        return userEmail + chocolateName;
    }




    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setChocolateName(String chocolateName) {
        this.chocolateName = chocolateName;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getGradeId() {
        return gradeId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getChocolateName() {
        return chocolateName;
    }

    public int getGrade() {
        return grade;
    }


    public ChocolateGrade() {
    }
}
