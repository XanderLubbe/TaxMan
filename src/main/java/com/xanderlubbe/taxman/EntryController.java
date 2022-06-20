package com.xanderlubbe.taxman;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
public class EntryController {
    private final EntryRepository repository;

    EntryController(EntryRepository repository) {
        this.repository = repository;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/entries")
    List<Entry> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/entries")
    Entry newEntry(@RequestBody Entry newEntry) {
        return repository.save(newEntry);
    }

    // Single item

    @GetMapping("/entries/{id}")
    Entry one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new EntryNotFoundException(id));
    }

    @PutMapping("/entries/{id}")
    Entry replaceEntry(@RequestBody Entry newEntry, @PathVariable Long id) {

        return repository.findById(id)
                .map(entry -> {
                    entry.setName(newEntry.getName());
                    entry.setRole(newEntry.getRole());
                    return repository.save(entry);
                })
                .orElseGet(() -> {
                    newEntry.setId(id);
                    return repository.save(newEntry);
                });
    }

    @DeleteMapping("/entries/{id}")
    void deleteEntry(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
