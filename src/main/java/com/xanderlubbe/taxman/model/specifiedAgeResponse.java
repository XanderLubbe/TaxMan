package com.xanderlubbe.taxman.model;

import lombok.Getter;

@Getter
public class SpecifiedAgeResponse {

    private final int amount;

    public SpecifiedAgeResponse(int amount){
        this.amount = amount;
    }

}
