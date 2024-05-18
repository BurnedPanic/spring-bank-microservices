package application.bank.accounts.mapper;

import application.bank.accounts.controller.dto.AccountDTO;
import application.bank.accounts.controller.dto.CustomerDTO;
import application.bank.accounts.controller.dto.CustomerDetailsDTO;
import application.bank.accounts.proxy.dto.CardDTO;
import application.bank.accounts.proxy.dto.LoanDTO;
import application.bank.accounts.repository.entities.Account;
import application.bank.accounts.repository.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {

    Customer mapToCustomer(CustomerDTO customerDTO);
    Customer updateCustomer(CustomerDTO customerDTO, @MappingTarget Customer customer);

    @Mapping(target = ".", source = "customer")
    @Mapping(target = "accountDTO", source = "account")
    CustomerDTO mapToCustomerDTO(Customer customer, Account account);

    @Mapping(target = ".", source = "customerDTO")
    @Mapping(target = "loanDTO", source = "loanDTO")
    @Mapping(target = "cardDTO", source = "cardDTO")
    @Mapping(target = "accountDTO", source = "accountDTO")
    CustomerDetailsDTO mapToCustomerDetailsDTO(CustomerDTO customerDTO, LoanDTO loanDTO, CardDTO cardDTO, AccountDTO accountDTO);

}
