package chocolate_recomendation.repository;

import demo.facts.User;
import demo.facts.UserRank;
import demo.facts.UserType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class UserRepository {

    private static UserRepository instance = new UserRepository();
    private List<User> users;

    private User loggedUser;

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }


    public static UserRepository getInstance() {
        return instance;
    }

    public UserRepository(){

            initUsers();

    }


    private void initUsers(){
        users = new ArrayList<>();
        User user1 = new User("lazar@gmail.com","lazar","Lazar", "Mijatovic" , "566566" , UserType.ADMINISTRATOR ,UserRank.NONE);
        User user2 = new User("none@gmail.com","none","none", "none" , "566566" , UserType.REGISTERED_USER ,UserRank.NONE);
        User user3 = new User("bronze@gmail.com","bronze","bronze", "bronze" , "566566" , UserType.REGISTERED_USER ,UserRank.BRONZE);
        User user4 = new User("silver@gmail.com","silver","silver", "silver" , "566566" , UserType.REGISTERED_USER ,UserRank.SILVER);
        User user5 = new User("gold@gmail.com","gold","gold", "gold" , "566566" , UserType.REGISTERED_USER ,UserRank.GOLD);
        User user6 = new User("platinum@gmail.com","platinum","platinum", "platinum" , "566566" , UserType.REGISTERED_USER ,UserRank.PLATINUM);

        this.users.add(user1);
        this.users.add(user2);
        this.users.add(user3);
        this.users.add(user4);
        this.users.add(user5);
        this.users.add(user6);

    }

    public List<User> getUsers(){
       return users;
    }


    public void addUser(User user){
        this.users.add(user);
    }
}
