package chocolate_recomendation.controller;
import chocolate_recomendation.service.ChocolateService;
import demo.facts.Chocolate;
import demo.facts.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/chocolates")
public class ChocolateController {

    private static Logger log = LoggerFactory.getLogger(ChocolateController.class);

    @Autowired
    private final ChocolateService chocolateService;


    @Autowired
    public ChocolateController(ChocolateService chocolateService) {
        this.chocolateService = chocolateService;
    }


    @RequestMapping(value = "/discount", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public List<Chocolate> findDiscountChocolate() {
        return chocolateService.getDiscountedChocolate();
        //return new ResponseEntity<>("{Hej}", HttpStatus.OK);
    }


    @RequestMapping(value = "/discount/{ammount}", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public List<Chocolate> findDiscountChocolateWithAmmount(@PathVariable int ammount) {
        return chocolateService.getDiscountedChocolateWithAmmount(ammount);
    }


    @RequestMapping( method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public List<Chocolate> findAll() {
        return chocolateService.getAll();
    }

    @RequestMapping(value = "/ingredients",method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public List<String> findAllIngredients() {
        return chocolateService.getAllIngredients();

    }


}
