package demo.facts;

import java.io.Serializable;
import java.util.Random;

public class Rule implements Serializable {

    private Long id;

    private String chocolateName;

    private int discount;

    public Rule(String chocolateName, int discount) {
        this.chocolateName = chocolateName;
        this.discount = discount;
        this.id = new Random().nextLong();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChocolateName() {
        return chocolateName;
    }

    public void setChocolateName(String chocolateName) {
        this.chocolateName = chocolateName;
    }


    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
