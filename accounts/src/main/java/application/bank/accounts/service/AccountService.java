package application.bank.accounts.service;

import application.bank.accounts.controller.dto.CustomerDTO;

public interface AccountService {

    void createAccount(CustomerDTO customerDTO);
    CustomerDTO getAccount(String mobileNumber);

    void updateAccount(CustomerDTO customerDTO);

    void deleteAccount(String mobileNumber);

}
