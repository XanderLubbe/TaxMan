package za.co.taxman.taxmanapi.tax;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
public class TaxController {

    private final TaxService service;

    TaxController(TaxService service) {
        this.service = service;
    }

    @GetMapping("/tax/personal")
    PersonalTaxResponse returnPersonalTax(
            @RequestParam int monthlyIncome,
            @RequestParam int age
    ) throws IOException {

        // TODO: Create meaningful error handling for exceptions
        // TODO: Don't throw IO Exception, handle it gracefully

        if (monthlyIncome < 0 || age < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Income and/or age cannot be negative.");
        }

        int taxAmount = service.calculateTaxAmount(monthlyIncome, age);

        return new PersonalTaxResponse(taxAmount);
    }
}
