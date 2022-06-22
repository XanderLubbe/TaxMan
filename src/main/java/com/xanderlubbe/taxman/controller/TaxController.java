package com.xanderlubbe.taxman.controller;
//
import com.xanderlubbe.taxman.model.UnspecifiedAgeResponse;
import com.xanderlubbe.taxman.model.SpecifiedAgeResponse;
import com.xanderlubbe.taxman.service.TaxService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaxController {
    private final TaxService service;

    TaxController(TaxService service) {
        this.service = service;
    }

    // Get all entries in the database currently
    @GetMapping("/tax")
    SpecifiedAgeResponse findTaxes(@RequestParam int salary, int age) {

        return service.findTaxesService(salary, age);
    }

    @GetMapping("/taxes")
    UnspecifiedAgeResponse findTaxes(@RequestParam int salary) {
        UnspecifiedAgeResponse taxesService = service.findTaxesService(salary);

        return taxesService;
    }

}
