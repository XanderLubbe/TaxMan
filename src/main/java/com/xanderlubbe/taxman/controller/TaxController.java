package com.xanderlubbe.taxman.controller;

import com.xanderlubbe.taxman.model.Tax;
import com.xanderlubbe.taxman.repository.TaxRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class TaxController {
    private final TaxRepository repository;

    TaxController(TaxRepository repository) {
        this.repository = repository;
    }

    // Get all entries in the database currently
    @GetMapping("/tax")
    Collection<Tax> findTaxes(@RequestParam int salary, int age) {
        return repository.findTaxes(salary);
    }

    @GetMapping("/taxes")
    Collection<Tax> findTaxes(@RequestParam int salary) {
        return repository.findTaxes(salary);
    }

}
