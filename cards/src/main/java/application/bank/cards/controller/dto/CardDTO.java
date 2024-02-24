package application.bank.cards.controller.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

@Valid
public record CardDTO (

        @NotBlank
        @Size(min = 10, max = 10, message = "Mobile number must have 10 digits")
        String mobileNumber,
        @NotBlank
        String cardNumber,
        @NotBlank
        String cardType,
        @Positive
        int totalLimit,
        @PositiveOrZero
        int amountUsed,
        @PositiveOrZero
        int availableAmount
) { }
