package com.sperandio.lukas.NewToDoList.dto.response;

public record UserResponse(
    long id,
    String username,
    String email,
    String firstName,
    String lastName
) {}
