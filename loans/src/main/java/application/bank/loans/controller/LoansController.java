package application.bank.loans.controller;

import application.bank.loans.config.LoansServiceConfig;
import application.bank.loans.controller.dto.LoanDTO;
import application.bank.loans.service.LoanService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
@Validated
public class LoansController {

    private final LoanService loanService;
    private final LoansServiceConfig loansServiceConfig;

    @Operation(
            summary = "Create Loan REST API",
            description = "REST API to create new loan inside EazyBank"
    )
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createLoan(@RequestParam
                           @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                           String mobileNumber) {

        loanService.createLoan(mobileNumber);
    }

    @Operation(
            summary = "Fetch Loan Details REST API",
            description = "REST API to fetch loan details based on a mobile number"
    )
    @GetMapping("/fetch")
    public LoanDTO fetchLoanDetails(@RequestParam
                                    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                    String mobileNumber) {
        return loanService.getLoan(mobileNumber);
    }

    @Operation(
            summary = "Update Loan Details REST API",
            description = "REST API to update loan details based on a loan number"
    )
    @PutMapping("/update")
    public void updateLoanDetails(@Valid @RequestBody LoanDTO loanDTO) {
        loanService.updateLoan(loanDTO);
    }

    @Operation(
            summary = "Delete Loan Details REST API",
            description = "REST API to delete Loan details based on a mobile number"
    )
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLoanDetails(@RequestParam
                                  @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                  String mobileNumber) {
        loanService.deleteLoan(mobileNumber);
    }


    @GetMapping("/loans/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        return ow.writeValueAsString(loansServiceConfig.toString());
    }

}
