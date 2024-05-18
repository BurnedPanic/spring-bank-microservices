package application.bank.accounts.proxy.dto;

public record LoanDTO(

        String loanNumber,
        String loanType,
        Integer totalLoan,
        Integer amountPaid,
        Integer outstandingAmount
) { }
