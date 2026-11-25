package com.authservice.aplication.port.out.persistense;

import com.authservice.aplication.dto.results.UserResult;

import java.util.UUID;

public interface FindUserByIdPort {
UserResult findById(UUID userId);
}
