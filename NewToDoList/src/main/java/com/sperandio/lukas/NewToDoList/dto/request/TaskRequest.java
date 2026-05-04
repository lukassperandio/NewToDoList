package com.sperandio.lukas.NewToDoList.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TaskRequest (
        @NotBlank
        @Size(min = 4, max = 50)
        String title,
        @NotBlank
        @Size(min = 5, max = 100)
        String description,
        @Min(1)
        @Max(5)
        int priority,
        boolean completed,
        @NotNull
        Long userId
){}
