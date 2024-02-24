package application.bank.cards.controller;

import application.bank.cards.controller.dto.CardDTO;
import application.bank.cards.service.CardService;
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
public class CardController {

    private final CardService cardService;

    @Operation(
            summary = "Create Card REST API",
            description = "REST API to create new Card inside EazyBank"
    )
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCard(@Valid @RequestParam
                           @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                           String mobileNumber) {
        cardService.createCard(mobileNumber);
    }

    @Operation(
            summary = "Fetch Card Details REST API",
            description = "REST API to fetch card details based on a mobile number"
    )
    @GetMapping("/fetch")
    public CardDTO fetchCardDetails(@RequestParam
                                    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                    String mobileNumber) {
        return cardService.fetchCard(mobileNumber);
    }

    @Operation(
            summary = "Update Card Details REST API",
            description = "REST API to update card details based on a card number"
    )
    @PutMapping("/update")
    public void updateCardDetails(@Valid @RequestBody CardDTO cardDTO) {
        cardService.updateCard(cardDTO);
    }

    @Operation(
            summary = "Delete Card Details REST API",
            description = "REST API to delete Card details based on a mobile number"
    )
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCardDetails(@RequestParam
                                  @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                  String mobileNumber) {
        cardService.deleteCard(mobileNumber);
    }

}