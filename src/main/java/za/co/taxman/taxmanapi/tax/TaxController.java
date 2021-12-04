package za.co.taxman.taxmanapi.tax;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaxController {

    @GetMapping("/tax/personal")
    boolean returnPersonalTax(income, age) {
        if(income){

        }
        return true;
    }

}
