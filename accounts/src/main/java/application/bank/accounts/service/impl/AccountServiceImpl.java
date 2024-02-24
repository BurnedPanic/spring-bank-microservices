package application.bank.accounts.service.impl;

import application.bank.accounts.controller.dto.AccountDTO;
import application.bank.accounts.controller.dto.CustomerDTO;
import application.bank.accounts.exception.CustomerAlreadyExistsException;
import application.bank.accounts.exception.ResourceNotFoundException;
import application.bank.accounts.mapper.AccountsMapper;
import application.bank.accounts.mapper.CustomerMapper;
import application.bank.accounts.repository.AccountRepository;
import application.bank.accounts.repository.CustomerRepository;
import application.bank.accounts.repository.entities.Account;
import application.bank.accounts.repository.entities.Customer;
import application.bank.accounts.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountsMapper accountsMapper;
    private final CustomerMapper customerMapper;

    @Override
    public void createAccount(CustomerDTO customerDTO) {
        Customer customer = customerMapper.mapToCustomer(customerDTO);

        Optional<Customer> optionalCustomer = customerRepository.findCustomerByMobileNumber(customerDTO.mobileNumber());

        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already exists with given mobile number " + customerDTO.mobileNumber());
        }

        Customer savedCustomer = customerRepository.save(customer);

        accountRepository.save(createAccount(savedCustomer.getCustomerId()));

    }

    @Override
    public CustomerDTO getAccount(String mobileNumber) {
        Customer customer = customerRepository.findCustomerByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        Account account = accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", String.valueOf(customer.getCustomerId())));

        return customerMapper.mapToCustomerDTO(customer, account);
    }

    @Override
    public void updateAccount(CustomerDTO customerDTO) {
        AccountDTO accountDTO = customerDTO.accountDTO();

        if (nonNull(accountDTO)) {
            Account account = accountRepository.findById(accountDTO.accountNumber())
                    .orElseThrow(() -> new ResourceNotFoundException("Account", "accountNumber", String.valueOf(accountDTO.accountNumber())));

            account = accountRepository.save(accountsMapper.mapToAccount(accountDTO, account));

            Long customerId = account.getCustomerId();

            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", String.valueOf(customerId)));

            customerRepository.save(customerMapper.updateCustomer(customerDTO, customer));
        } else {
            throw new RuntimeException("Bad Request");
        }

    }

    @Override
    public void deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findCustomerByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        accountRepository.deleteAccountByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
    }

    private Account createAccount(long customerId) {
        long accountNumber = 1000000000L + new Random().nextLong(9000000000L);
        Account account = new Account(customerId, accountNumber, "SAVINGS", "Av Julio Dinis, 10");

        return account;
    }

}
