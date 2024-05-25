package application.bank.accounts.controller;

import application.bank.accounts.controller.dto.CustomerDetailsDTO;
import application.bank.accounts.service.CustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
@Validated
@Tag(name = "REST API for Customers")
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDTO> fetchCustomerDetails(@RequestHeader("burnedpanic-correlation-id") String correlationId,
                                                                   @RequestParam
                                                                   @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits")
                                                                   String mobileNumber) {
        logger.debug("burnedpanic-correlation-id found: {}", correlationId);

        return ResponseEntity.ok(customerService.fetchCustomerDetails(mobileNumber, correlationId));
    }

}
