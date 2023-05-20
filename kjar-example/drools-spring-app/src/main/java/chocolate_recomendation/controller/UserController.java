package chocolate_recomendation.controller;


//import chocolate_recomendation.service.UserService;

import demo.facts.Item;
import demo.facts.User;
import chocolate_recomendation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import demo.facts.User;

import java.util.List;


//@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {



  //  private static Logger log = (Logger) LoggerFactory.getLogger(UserController.class);

  @Autowired
    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }




    @RequestMapping( method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public List<User> findAll() {
        List<User> u =  userService.getUsers();
        return u;
        //return new ResponseEntity<>("{Hej}", HttpStatus.OK);
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> login(@RequestBody User user) {
        return new ResponseEntity<>(userService.login(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> register(@RequestBody User user) {
        return new ResponseEntity<>(userService.register(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/favourite/ingredients",method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public List<String> findAllFavouriteIngredients() {
        return userService.getAllFavouriteIngredients();

    }

    @RequestMapping(value = "/disliked/ingredients",method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public List<String> findAllDislikedIngredients() {
        return userService.getAllDislikedIngredients();

    }

    @RequestMapping(value = "/add/favourite/{ingredient}", method = RequestMethod.PUT)
    public ResponseEntity<?> addFavouriteIngredient(@PathVariable String ingredient) {
        userService.addFavouriteIngredient(ingredient);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "/remove/favourite/{ingredient}", method = RequestMethod.PUT)
    public ResponseEntity<?> removeFavouriteIngredient(@PathVariable String ingredient) {
        userService.removeFavouriteIngredient(ingredient);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "/add/disliked/{ingredient}", method = RequestMethod.PUT)
    public ResponseEntity<?> addDislikedIngredient(@PathVariable String ingredient) {
        userService.addDislikedIngredient(ingredient);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "/remove/disliked/{ingredient}", method = RequestMethod.PUT)
    public ResponseEntity<?> removeDislikedIngredient(@PathVariable String ingredient) {
        userService.removeDislikedIngredient(ingredient);
        return new ResponseEntity<>(HttpStatus.OK);
    }







}
