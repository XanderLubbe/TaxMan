package com.xanderlubbe.taxman;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "entry")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entry {
    private @Id @GeneratedValue long id;
    private int lowerLimit;
    private int upperLimit;
    private int tax1;
    private int tax2;
    private int tax3;

    Entry(int lowerLimit, int upperLimit, int tax1, int tax2, int tax3) {

        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.tax1 = tax1;
        this.tax2 = tax2;
        this.tax3 = tax3;
    }
}
