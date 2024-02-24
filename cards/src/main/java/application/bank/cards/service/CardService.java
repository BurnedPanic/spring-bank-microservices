package application.bank.cards.service;

import application.bank.cards.controller.dto.CardDTO;

public interface CardService {

    void createCard(String mobileNumber);

    CardDTO fetchCard(String mobileNumber);
    void updateCard(CardDTO cardDTO);
    void deleteCard(String mobileNumber);

}
