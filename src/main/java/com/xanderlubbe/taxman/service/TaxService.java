package com.xanderlubbe.taxman.service;

import com.xanderlubbe.taxman.model.TaxDTO;
import com.xanderlubbe.taxman.model.unspecifiedAgeResponse;
import com.xanderlubbe.taxman.model.specifiedAgeResponse;
import com.xanderlubbe.taxman.repository.TaxRepository;
import org.springframework.stereotype.Service;

@Service
public class TaxService {

    private final TaxRepository repository;

    TaxService(TaxRepository repository) {
        this.repository = repository;
    }

    public specifiedAgeResponse findTaxesService(int salary, int age){

        TaxDTO repoTaxDTO = repository.findTaxesRepo(salary);
        int returnedAmount;
        if (age < 65 ){

             returnedAmount = repoTaxDTO.getTaxUnder65();

        } else if (age >= 65 && age < 75) {

            returnedAmount = repoTaxDTO.getTaxUnder74();

        } else {

            returnedAmount = repoTaxDTO.getTaxOver75();

        }

        return new specifiedAgeResponse(returnedAmount);
    }

    public unspecifiedAgeResponse findTaxesService(int salary){

        TaxDTO repoTaxDTO = repository.findTaxesRepo(salary);

        int returnedTaxUnder65 = repoTaxDTO.getTaxUnder65();
        int returnedTaxUnder74 = repoTaxDTO.getTaxUnder74();
        int returnedTaxOver75 = repoTaxDTO.getTaxOver75();
        return new unspecifiedAgeResponse(returnedTaxUnder65, returnedTaxUnder74, returnedTaxOver75);

//        return  repository.findTaxesRepo(salary);
    }
}
