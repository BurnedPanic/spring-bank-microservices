package application.bank.accounts.controller;

import application.bank.accounts.controller.dto.CustomerDetailsDTO;
import application.bank.accounts.service.CustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
@Validated
@Tag(name = "REST API for Customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDTO> fetchCustomerDetails(@RequestParam
                                                                    @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits")
                                                                    String mobileNumber) {
        return ResponseEntity.ok(customerService.fetchCustomerDetails(mobileNumber));
    }

}
