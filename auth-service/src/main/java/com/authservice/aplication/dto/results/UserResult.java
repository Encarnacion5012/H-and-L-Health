package com.authservice.aplication.dto.results;

import java.util.List;

public record UserResult(

        String username,


        String email,

        String profile_photo,

        List<String> roles
) {
}
