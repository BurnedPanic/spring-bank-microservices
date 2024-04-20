package application.bank.accounts.controller;

import application.bank.accounts.config.AccountServiceConfig;
import application.bank.accounts.controller.dto.CustomerDTO;
import application.bank.accounts.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
@Validated
@Tag(name = "CRUD REST API for Accounts")
public class AccountsController {

//    @Value("${account}")
//    private String account;

    private final AccountService accountService;
    private final AccountServiceConfig accountServiceConfig;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create Account REST API")
    public void createAccount(@RequestBody @Valid CustomerDTO customerDTO) {
        accountService.createAccount(customerDTO);
    }

    @GetMapping("/accounts/{mobileNumber}")
    @Operation(summary = "Get Account Details REST API")
    public CustomerDTO getAccount(@PathVariable
                                      @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits")
                                      String mobileNumber) {
        return accountService.getAccount(mobileNumber);
    }

    @PutMapping("/accounts/update")
    @Operation(summary = "Update Account Details REST API")
    public void updateAccount(@RequestBody @Valid CustomerDTO customerDTO) {
        accountService.updateAccount(customerDTO);
    }

    @DeleteMapping("/accounts/{mobileNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete Account and Customer Details REST API")
    public void deleteAccount(@PathVariable
                                  @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits")
                                  String mobileNumber) {
        accountService.deleteAccount(mobileNumber);
    }

    @GetMapping("/accounts/properties")
    public AccountServiceConfig getPropertyDetails() {
        return accountServiceConfig;
    }

}
