package chocolate_recomendation.controller;
import chocolate_recomendation.service.ChocolateService;
import demo.facts.Chocolate;
import demo.facts.ChocolatePurchase;
import org.apache.maven.shared.invoker.MavenInvocationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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




    @RequestMapping(value = "/discount/{amount}", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public List<Chocolate> findDiscountChocolateWithAmount(@PathVariable int amount) {
        return chocolateService.getDiscountedChocolateWithAmmount(amount);
    }


    @RequestMapping( method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public List<Chocolate> findAll() {
        return chocolateService.getAll();
    }


    @RequestMapping(value = "/purchases", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public List<ChocolatePurchase> findAllPurchases() {
        return chocolateService.getChocolatePurchases();
    }


    @RequestMapping(value = "/find/{chocolateName}", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public Chocolate findOneByName(@PathVariable String chocolateName) {

        return chocolateService.getOneByName(chocolateName);
    }

    @RequestMapping(value = "/find/{chocolateName}/{amount}", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public Chocolate findOneByNameWithAmount(@PathVariable String chocolateName , @PathVariable int amount) {

        return chocolateService.getOneByNameWithDiscount(chocolateName,amount);
    }


    @RequestMapping(value = "/ingredients",method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public List<String> findAllIngredients() {
        return chocolateService.getAllIngredients();

    }

    @RequestMapping(value = "/grade/{chocolateName}/{grade}", method = RequestMethod.POST)
    public ResponseEntity<?> register(@PathVariable String chocolateName,@PathVariable int grade) {
        return new ResponseEntity<>(chocolateService.addOrUpdateChocolateGrade(chocolateName,grade), HttpStatus.OK);
    }



    @RequestMapping(value = "/purchase/{chocolateName}/{amount}", method = RequestMethod.POST)
    public ResponseEntity<?> addChocolatePurchase(@PathVariable String chocolateName,@PathVariable int amount) {
        return new ResponseEntity<>(chocolateService.addChocolatePurchase(chocolateName,amount), HttpStatus.OK);
    }

    @RequestMapping(value = "/unregistered", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public List<Chocolate> getChocolatesForUnregisteredUsers() {
        return chocolateService.getChocolatesForUnregisteredUsers();
    }

    @RequestMapping(value = "/rule/{chocolateName}/{discount}", method = RequestMethod.POST)
    public ResponseEntity<?> addDiscountRule(@PathVariable String chocolateName,@PathVariable int discount) throws MavenInvocationException {

        return new ResponseEntity<>(chocolateService.addChocolateDiscountRule(chocolateName,discount), HttpStatus.OK);
    }




}
