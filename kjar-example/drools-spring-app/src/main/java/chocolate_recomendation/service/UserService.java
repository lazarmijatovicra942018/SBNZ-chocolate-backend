package chocolate_recomendation.service;

import chocolate_recomendation.repository.UserRepository;
import demo.facts.User;
import demo.facts.UserRank;
import demo.facts.UserType;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService {



    private final KieContainer kieContainer;

    private final UserRepository repository = UserRepository.getInstance();

    @Autowired
    public UserService(KieContainer kieContainer) {

        this.kieContainer = kieContainer;
    }

    public List<User> getUsers() {

        return repository.getUsers();
    }

    public Object login(User user) {
        List<User> users = repository.getUsers();
        User retVal = users.stream().filter(u->u.getEmail().trim().equals(user.getEmail().trim()) && u.getPassword().trim().equals(user.getPassword().trim())).findFirst().orElse(null);

        if (retVal != null) {
            this.repository.setLoggedUser(retVal);
            User retValCopy = new User(retVal);
            retVal.Loging();
            return retValCopy;
        }
        else if (users.stream().filter(u->u.getEmail().trim().equals(user.getEmail().trim())).findFirst().orElse(null) != null) {
            return "\"" + " Password is incorrect !" + "\"";
        }
        else {
            return "\"" + " Email you entered does not exist !" + "\"";
        }
    }



    public Object register(User user) {

        List<User> users = repository.getUsers();
        User retVal = users.stream().filter(u->u.getEmail().equals(user.getEmail())).findFirst().orElse(null);

        if (retVal == null) {
            user.setUserRank(UserRank.NONE);
            user.setUserType(UserType.REGISTERED_USER);
            this.repository.addUser(user);
            return user;
        }
        else {
            return "\"" + " Email you entered already exist !" + "\"";
        }
    }


    public List<String> getAllFavouriteIngredients(){

        return repository.getLoggedUser().getFavouriteIngredients();
    }

    public void  addFavouriteIngredient(String ingredient){

        repository.getLoggedUser().addFavouriteIngredient(ingredient);
    }

    public void  removeFavouriteIngredient(String ingredient){

        repository.getLoggedUser().removeFavouriteIngredient(ingredient);
    }


    public void  addDislikedIngredient(String ingredient){

        repository.getLoggedUser().addDislikedIngredient(ingredient);
    }

    public void  removeDislikedIngredient(String ingredient){

        repository.getLoggedUser().removeDislikedIngredient(ingredient);
    }



    public List<String> getAllDislikedIngredients(){
        return repository.getLoggedUser().getDislikedIngredients();
    }

}
