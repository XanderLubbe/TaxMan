package com.xanderlubbe.taxman.model;

import lombok.Getter;

@Getter
public class specifiedAgeResponse {

    private final int amount;

    public specifiedAgeResponse(int amount){
        this.amount = amount;
    }

}
