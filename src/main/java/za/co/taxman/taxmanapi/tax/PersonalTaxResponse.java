package za.co.taxman.taxmanapi.tax;

import java.math.BigDecimal;

public class PersonalTaxResponse {

    public int taxAmount;

    PersonalTaxResponse(int taxAmount) {
        this.taxAmount = taxAmount;
    }
}
