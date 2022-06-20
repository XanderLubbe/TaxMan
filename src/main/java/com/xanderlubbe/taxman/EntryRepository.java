package com.xanderlubbe.taxman;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface EntryRepository extends JpaRepository<Entry, Long> {

    @Query("SELECT e FROM Entry e")
    Collection<Entry> getThings();

}
