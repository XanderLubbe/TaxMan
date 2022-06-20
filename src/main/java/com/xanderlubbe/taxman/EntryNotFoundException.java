package com.xanderlubbe.taxman;

public class EntryNotFoundException extends RuntimeException {
    EntryNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
