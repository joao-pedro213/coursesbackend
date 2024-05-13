package com.joaocapobiango.coursesbackend.exception;

public class ResourceNotFound extends RuntimeException {

    public ResourceNotFound(Long id) {
        super("Course with id %s not found".formatted(id));
    }

}