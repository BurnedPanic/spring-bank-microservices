package application.bank.loans.service;

import application.bank.loans.controller.dto.LoanDTO;

public interface LoanService {

    LoanDTO getLoan(String mobileNumber);
    void createLoan(String mobileNumber);
    void updateLoan(LoanDTO loanDTO);
    void deleteLoan(String mobileNumber);

}
