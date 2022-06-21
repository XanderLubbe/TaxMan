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
public class Tax {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long id;
    private int lowerLimit;
    private int upperLimit;
    private int tax1;
    private int tax2;
    private int tax3;

    public Tax(int lowerLimit, int upperLimit, int tax1, int tax2, int tax3) {

        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.tax1 = tax1;
        this.tax2 = tax2;
        this.tax3 = tax3;
    }
}
