package com.xanderlubbe.taxman;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
public class EntryController {
    private final EntryRepository repository;

    EntryController(EntryRepository repository) {
        this.repository = repository;
    }

    // Get all entries in the database currently
    @GetMapping("/entries")
    List<Entry> all() {
        return new ArrayList<>(repository.getThings());
    }

    // Create a new entry in the database (must use body as JSON)
    @PostMapping("/entries")
    Entry newEntry(@RequestBody Entry newEntry) {
        return repository.save(newEntry);
    }

    // Get an entry in the database with this id
    @GetMapping("/entries/{id}")
    Entry one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new EntryNotFoundException(id));
    }
    // Update an entry in the database with this id
    @PutMapping("/entries/{id}")
    Entry replaceEntry(@RequestBody Entry newEntry, @PathVariable Long id) {

        return repository.findById(id)
                .map(entry -> {
                    entry.setLowerLimit(newEntry.getLowerLimit());
                    entry.setUpperLimit(newEntry.getUpperLimit());
                    return repository.save(entry);
                })
                .orElseGet(() -> {
                    newEntry.setId(id);
                    return repository.save(newEntry);
                });
    }

    // Delete entry with this id
    @DeleteMapping("/entries/{id}")
    void deleteEntry(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
