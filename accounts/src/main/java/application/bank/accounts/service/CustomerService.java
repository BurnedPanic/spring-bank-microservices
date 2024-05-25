package application.bank.accounts.service;

import application.bank.accounts.controller.dto.CustomerDetailsDTO;

public interface CustomerService {

    CustomerDetailsDTO fetchCustomerDetails(String mobileNumber, String correlationId);

}
