package com.cinemate.backend.exception;

public class WatchSessionNotFoundException extends ResourceNotFoundException {
    public WatchSessionNotFoundException(Long id) {
        super("WatchSession not found with id: " + id);
    }
}