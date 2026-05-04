package com.sperandio.lukas.NewToDoList.dto.response;

public record TaskResponse (
        long id,
        String title,
        String description,
        int priority,
        boolean completed,
        long userId
) {}
