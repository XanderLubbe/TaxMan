package com.xanderlubbe.taxman.model;

import lombok.Getter;

@Getter
public class UnspecifiedAgeResponse {

    private final int amountUnder65;

    private final int amountUnder74;

    private final int amountOver75;

    public UnspecifiedAgeResponse(int amountUnder65, int amountUnder74, int amountOver75){
        this.amountUnder65 = amountUnder65;
        this.amountUnder74 = amountUnder74;
        this.amountOver75 = amountOver75;
    }


}
