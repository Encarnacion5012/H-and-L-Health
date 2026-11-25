package com.authservice.aplication.port.out.persistense;

import com.authservice.domain.model.User;

public interface CreateDoctorPort {
    void createDoctor(User user);
}
