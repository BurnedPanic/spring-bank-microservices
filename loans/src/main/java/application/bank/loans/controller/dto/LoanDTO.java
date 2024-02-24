package application.bank.loans.controller.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

@Valid
public record LoanDTO(

        @NotBlank
        @Size(min = 10, max = 10, message = "Mobile number must have 10 digits")
        String mobileNumber,
        @NotBlank
        @Size(min = 12, max = 12, message = "Loan number must have 12 digits")
        String loanNumber,
        @NotBlank
        String loanType,
        @Positive
        Integer totalLoan,
        @PositiveOrZero
        Integer amountPaid,
        @PositiveOrZero
        Integer outstandingAmount
) { }
