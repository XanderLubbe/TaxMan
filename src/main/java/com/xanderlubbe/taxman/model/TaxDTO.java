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
    private int taxUnder65;
    private int taxUnder74;
    private int taxOver75;

    public TaxDTO(int lowerLimit, int upperLimit, int taxUnder65, int taxUnder74, int taxOver75) {

        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.taxUnder65 = taxUnder65;
        this.taxUnder74 = taxUnder74;
        this.taxOver75 = taxOver75;
    }
}
