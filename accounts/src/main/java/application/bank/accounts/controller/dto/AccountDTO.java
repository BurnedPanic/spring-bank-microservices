package application.bank.accounts.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

@Schema(name = "Account", description = "Schema that represents an Account")
public record AccountDTO(

        @NotNull
        @Range(min = 1000000000L, max = 9999999999L, message = "Account number must be 10 digits")
        Long accountNumber,
        @NotBlank(message = "AccountType can not be null or empty")
        @Schema(example = "SAVINGS")
        String accountType,
        @NotBlank(message = "Branch Address can not be null or empty")
        String branchAddress
) {
}
