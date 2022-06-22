package com.xanderlubbe.taxman.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.xanderlubbe.taxman.Constants.TAX_TABLE_NAME;

@Entity
@Table(name = TAX_TABLE_NAME)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaxDTO {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long id;
    private int lowerLimit;
    private int upperLimit;
    private int amountUnder65;
    private int amountUnder74;
    private int amountOver75;

    public TaxDTO(int lowerLimit, int upperLimit, int amountUnder65, int amountUnder74, int amountOver75) {

        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.amountUnder65 = amountUnder65;
        this.amountUnder74 = amountUnder74;
        this.amountOver75 = amountOver75;
    }
}
