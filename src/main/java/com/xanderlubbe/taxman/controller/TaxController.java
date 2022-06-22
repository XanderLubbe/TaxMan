package com.xanderlubbe.taxman.controller;
//
import com.xanderlubbe.taxman.model.Tax;
import com.xanderlubbe.taxman.repository.TaxRepository;
import com.xanderlubbe.taxman.service.TaxService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//
import java.util.Collection;

@RestController
public class TaxController {
    private final TaxService service;

    TaxController(TaxService service) {
        this.service = service;
    }

    // Get all entries in the database currently
    @GetMapping("/tax")
    Collection<Tax> findTaxes(@RequestParam int salary, int age) {
        return service.findTaxesService(salary, age);
    }

    @GetMapping("/taxes")
    Collection<Tax> findTaxes(@RequestParam int salary) {
        return service.findTaxesService(salary);
    }

}
