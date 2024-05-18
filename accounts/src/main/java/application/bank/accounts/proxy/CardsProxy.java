package application.bank.accounts.proxy;

import application.bank.accounts.proxy.dto.CardDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cards")
public interface CardsProxy {

    @GetMapping("/api/fetch")
    ResponseEntity<CardDTO> fetchCardDetails(@RequestParam String mobileNumber);


}
