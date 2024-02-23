package com.chiarapuleio.eventmanagement.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id) {
        super(id + " not found.");
    }

    public NotFoundException(String message) {
        super(message);
    }
}