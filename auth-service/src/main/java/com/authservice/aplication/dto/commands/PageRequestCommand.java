package com.authservice.aplication.dto.commands;

import org.hibernate.query.SortDirection;

public record PageRequestCommand(


        int page,
        int size,
        String sortBy,
        SortDirection sortDirection
) {

    public PageRequestCommand{
        if (page < 0) {
            throw new IllegalArgumentException("Page number must be greater than zero");
        }
    }
}
