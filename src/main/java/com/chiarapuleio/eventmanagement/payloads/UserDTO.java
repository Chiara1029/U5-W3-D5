package com.chiarapuleio.eventmanagement.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserDTO (
        @NotEmpty(message = "Name required.")
        @Size(min=2, max=20, message = "Name must have at least 2 characters.")
        String name,

        @NotEmpty(message = "Surname required.")
        @Size(min=2, max=20, message = "Surname must have at least 2 characters.")
        String surname,

        @NotEmpty(message = "Email required.")
        @Email(message = "Invalid email.")
        String email,

        @NotEmpty(message = "Password required.")
        @Size(min=10, max=20, message = "Password must have at least 10 characters.")
        String password
) {
}
