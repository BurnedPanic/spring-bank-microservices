package application.bank.accounts.proxy;

import application.bank.accounts.proxy.dto.LoanDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("loans")
public interface LoansProxy {

    @GetMapping("/api/fetch")
    ResponseEntity<LoanDTO> fetchLoanDetails(@RequestParam String mobileNumber);


}
