package com.xanderlubbe.taxman.repository;

import com.xanderlubbe.taxman.model.TaxDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import static com.xanderlubbe.taxman.Constants.TAX_TABLE_NAME;

public interface TaxRepository extends JpaRepository<TaxDTO, Long> {

      @Query("SELECT e FROM " + TAX_TABLE_NAME + " e WHERE e.lowerLimit <= :salary AND e.upperLimit >= :salary")
      TaxDTO findTaxesRepo(@Param("salary") int salary);

}
