package com.xanderlubbe.taxman.model;

import lombok.Getter;

@Getter
public class unspecifiedAgeResponse {

    private final int taxUnder65;

    private final int taxUnder74;

    private final int taxOver75;

    public unspecifiedAgeResponse(int taxUnder65, int taxUnder74, int taxOver75){
        this.taxUnder65 = taxUnder65;
        this.taxUnder74 = taxUnder74;
        this.taxOver75 = taxOver75;
    }


}
