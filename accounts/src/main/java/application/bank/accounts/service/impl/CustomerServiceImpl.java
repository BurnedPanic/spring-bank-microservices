package application.bank.accounts.service.impl;

import application.bank.accounts.controller.dto.CustomerDetailsDTO;
import application.bank.accounts.exception.ResourceNotFoundException;
import application.bank.accounts.mapper.AccountsMapper;
import application.bank.accounts.mapper.CustomerMapper;
import application.bank.accounts.proxy.CardsProxy;
import application.bank.accounts.proxy.LoansProxy;
import application.bank.accounts.proxy.dto.CardDTO;
import application.bank.accounts.proxy.dto.LoanDTO;
import application.bank.accounts.repository.AccountRepository;
import application.bank.accounts.repository.CustomerRepository;
import application.bank.accounts.repository.entities.Account;
import application.bank.accounts.repository.entities.Customer;
import application.bank.accounts.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final LoansProxy loansProxy;
    private final CardsProxy cardsProxy;
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final AccountsMapper accountsMapper;


    @Override
    public CustomerDetailsDTO fetchCustomerDetails(String mobileNumber, String correlationId) {
        Customer customer = customerRepository.findCustomerByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        Account account = accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", String.valueOf(customer.getCustomerId())));

        LoanDTO loanDTO = loansProxy.fetchLoanDetails(mobileNumber, correlationId).getBody();
        CardDTO cardDTO = cardsProxy.fetchCardDetails(mobileNumber, correlationId).getBody();

        return customerMapper.mapToCustomerDetailsDTO(
                customerMapper.mapToCustomerDTO(customer, account),
                loanDTO,
                cardDTO,
                accountsMapper.mapToAccountDTO(account)
        );
    }
}
