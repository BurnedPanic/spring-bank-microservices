package application.bank.cards.service.impl;

import application.bank.cards.controller.dto.CardDTO;
import application.bank.cards.exception.CardAlreadyExistsException;
import application.bank.cards.exception.ResourceNotFoundException;
import application.bank.cards.mapper.CardMapper;
import application.bank.cards.repository.CardRepository;
import application.bank.cards.repository.entity.Card;
import application.bank.cards.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    @Override
    public void createCard(String mobileNumber) {
        Optional<Card> optionalCard = cardRepository.findByMobileNumber(mobileNumber);

        if (optionalCard.isPresent()) {
            throw new CardAlreadyExistsException("Card already registered with given mobileNumber "+mobileNumber);
        }

        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        Card card = new Card(mobileNumber, String.valueOf(randomCardNumber), "CREDIT", 1000, 0, 1000);

        cardRepository.save(card);
    }

    @Override
    public CardDTO fetchCard(String mobileNumber) {
        Card card = getCard(mobileNumber);

        return cardMapper.mapToCardDTO(card);
    }

    @Override
    public void updateCard(CardDTO cardDTO) {
        Card card = cardRepository.findByCardNumber(cardDTO.cardNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Card", "cardNumber", String.valueOf(cardDTO.cardNumber())));

        cardMapper.mapToCard(cardDTO, card);

        cardRepository.save(card);
    }

    @Override
    public void deleteCard(String mobileNumber) {
        Card card = getCard(mobileNumber);

        cardRepository.delete(card);
    }

    private Card getCard(String mobileNumber) {
        return cardRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));
    }
}
