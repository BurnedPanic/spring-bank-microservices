package application.bank.cards.mapper;

import application.bank.cards.controller.dto.CardDTO;
import application.bank.cards.repository.entity.Card;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardMapper {

    CardDTO mapToCardDTO(Card card);

    Card mapToCard(CardDTO cardDTO, @MappingTarget Card card);

}
