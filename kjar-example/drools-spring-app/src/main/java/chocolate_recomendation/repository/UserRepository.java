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
        User user1 = new User("lazar@gmail.com","lazar","Lazar", "Mijatovic" , "566577" , UserType.ADMINISTRATOR ,UserRank.NONE);
        User user2 = new User("marko@gmail.com","marko","Marko", "Mijatovic" , "566577" , UserType.REGISTERED_USER ,UserRank.NONE);
        this.loggedUser = user2;
        this.users.add(user1);
        this.users.add(user2);

    }

    public List<User> getUsers(){
       return users;
    }


    public void addUser(User user){
        this.users.add(user);
    }
}
