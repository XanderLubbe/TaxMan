package za.co.taxman.taxmanapi.tax;

import java.math.BigDecimal;

public class PersonalTaxResponse {

    public int monthlyIncome;
    public int age;

    PersonalTaxResponse(int monthlyIncome, int age) {
        this.monthlyIncome = monthlyIncome;
        this.age = age;
    }
}
