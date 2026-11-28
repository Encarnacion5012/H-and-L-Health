package com.authservice.aplication.dto.results;

import java.util.List;

public record PageResult <T>(
        List<T> content,
        int page,
        int size,
        Long totalElements,
        int totalPages
) {
}
