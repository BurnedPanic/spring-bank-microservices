package application.bank.accounts.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(name = "Customer", description = "Schema that represents a Customer and it's related Account")
public record CustomerDTO(
        @NotBlank(message = "Name can not be null or empty")
        @Size(min = 5, max = 30, message = "The length of the Customer Name should be between 5 and 30 characters")
        @Schema(example = "Mateus Maia")
        String name,
        @NotBlank(message = "Email can not be null or empty")
        @Email(message = "Mal formed email")
        String email,
        @NotBlank
        @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits")
        String mobileNumber,
        @Valid
        AccountDTO accountDTO
) {
}
