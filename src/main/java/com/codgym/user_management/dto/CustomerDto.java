package com.codgym.user_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomerDto {
    @NotNull(message = "Id must not be empty")
    @NotEmpty(message = "Name must not be empty")
    private String name;
    @NotNull(message = "Email must not be empty")
    @NotEmpty(message = "Name must not be empty")
    @Email(message = "Email must be valid")
    private String email;
    @NotNull(message = "Address must not be empty")
    @NotEmpty(message = "Address must not be empty")
    private String address;
}
