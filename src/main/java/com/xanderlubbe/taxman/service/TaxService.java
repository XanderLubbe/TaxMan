package com.xanderlubbe.taxman.service;

import com.xanderlubbe.taxman.model.Tax;
import com.xanderlubbe.taxman.repository.TaxRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TaxService {

    private final TaxRepository repository;

    TaxService(TaxRepository repository) {
        this.repository = repository;
    }

    public Collection<Tax> findTaxesService(int salary){
        return  repository.findTaxesRepo(salary);
    }

    public Collection<Tax> findTaxesService(int salary, int age){
        return  repository.findTaxesRepo(salary);
    }
}
