package com.authservice.domain.exception;

import lombok.Getter;

@Getter
public class DuplicatedResourceException extends RuntimeException {
    private final String field;
    public DuplicatedResourceException(String field, String message) {
        super(message);
        this.field = field;
    }


}
