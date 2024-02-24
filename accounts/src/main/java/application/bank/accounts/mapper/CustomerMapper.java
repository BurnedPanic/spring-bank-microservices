package application.bank.accounts.mapper;

import application.bank.accounts.controller.dto.CustomerDTO;
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

}
