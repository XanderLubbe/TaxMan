package com.xanderlubbe.taxman.service;

import com.xanderlubbe.taxman.model.TaxDTO;
import com.xanderlubbe.taxman.model.UnspecifiedAgeResponse;
import com.xanderlubbe.taxman.model.SpecifiedAgeResponse;
import com.xanderlubbe.taxman.repository.TaxRepository;
import org.springframework.stereotype.Service;

@Service
public class TaxService {

    private final TaxRepository repository;

    TaxService(TaxRepository repository) {
        this.repository = repository;
    }

    public SpecifiedAgeResponse findTaxesService(int salary, int age){

        TaxDTO repoTaxDTO = repository.findTaxesRepo(salary);
        int returnedAmount;
        if (age < 65 ){

             returnedAmount = repoTaxDTO.getAmountUnder65();

        } else if (age >= 65 && age < 75) {

            returnedAmount = repoTaxDTO.getAmountUnder74();

        } else {

            returnedAmount = repoTaxDTO.getAmountOver75();

        }

        return new SpecifiedAgeResponse(returnedAmount);
    }

    public UnspecifiedAgeResponse findTaxesService(int salary){

        TaxDTO repoTaxDTO = repository.findTaxesRepo(salary);

        int returnedTaxUnder65 = repoTaxDTO.getAmountUnder65();
        int returnedTaxUnder74 = repoTaxDTO.getAmountUnder74();
        int returnedTaxOver75 = repoTaxDTO.getAmountOver75();
        return new UnspecifiedAgeResponse(returnedTaxUnder65, returnedTaxUnder74, returnedTaxOver75);

//        return  repository.findTaxesRepo(salary);
    }
}
