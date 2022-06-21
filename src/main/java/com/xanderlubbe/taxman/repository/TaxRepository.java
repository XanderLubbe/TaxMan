package com.xanderlubbe.taxman.repository;

import com.xanderlubbe.taxman.model.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

import static com.xanderlubbe.taxman.Constants.TAX_TABLE_NAME;

public interface TaxRepository extends JpaRepository<Tax, Long> {

    @Query("SELECT e FROM " + TAX_TABLE_NAME + " e where e.lowerLimit <= :salary AND e.upperLimit >= :salary" )
    Collection<Tax> findTaxes(@Param("salary") int salary);

}
