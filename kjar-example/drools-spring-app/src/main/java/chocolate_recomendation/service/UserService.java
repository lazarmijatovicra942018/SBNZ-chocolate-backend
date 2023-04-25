package chocolate_recomendation.service;

import chocolate_recomendation.repository.UserRepository;
import demo.facts.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getUsers() {

        return repository.getUsers();
    }

    public Object login(User user) {
        List<User> users = repository.getUsers();
        User retVal = users.stream().filter(u->u.getEmail().equals(user.getEmail()) && u.getPassword().equals(user.getPassword())).findFirst().orElse(null);

        if (retVal != null) {
            this.repository.setLoggedUser(user);
            return retVal;
        }
        else if (users.stream().filter(u->u.getEmail().equals(user.getEmail())).findFirst().orElse(null) != null) {
            return "\"" + " Password is incorect !" + "\"";
        }
        else {
            return "\"" + " Email you entered does not exist !" + "\"";
        }
    }



    public Object register(User user) {

        List<User> users = repository.getUsers();
        User retVal = users.stream().filter(u->u.getEmail().equals(user.getEmail())).findFirst().orElse(null);

        if (retVal == null) {
            this.repository.addUser(user);
            return user;
        }
        else {
            return "\"" + " Email you entered already exist !" + "\"";
        }
    }



}
