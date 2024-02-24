package application.bank.accounts.mapper;

import application.bank.accounts.controller.dto.AccountDTO;
import application.bank.accounts.repository.entities.Account;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountsMapper {

    Account mapToAccount(AccountDTO accountDTO, @MappingTarget Account account);
    AccountDTO mapToAccountDTO(Account account);

}
