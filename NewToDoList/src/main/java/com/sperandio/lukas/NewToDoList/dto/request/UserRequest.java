package com.sperandio.lukas.NewToDoList.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotBlank
        @Size(min = 4, max = 25)
        String username,
        @Email
        @NotBlank
        String email,
        @Size(min = 4, max = 15)
        @NotBlank
        String firstName,
        @NotBlank
        @Size(min = 4, max = 20)
        String lastName,
        @NotBlank
        @Size(min = 4, max = 25)
        String password
) {}