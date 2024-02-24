package application.bank.loans.service.impl;

import application.bank.loans.controller.dto.LoanDTO;
import application.bank.loans.exception.LoanAlreadyExistsException;
import application.bank.loans.exception.ResourceNotFoundException;
import application.bank.loans.mapper.LoanMapper;
import application.bank.loans.repository.LoansRepository;
import application.bank.loans.repository.entity.Loan;
import application.bank.loans.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoansRepository loansRepository;
    private final LoanMapper loanMapper;


    @Override
    public LoanDTO getLoan(String mobileNumber) {
        Loan loan = getExistentLoan(mobileNumber);

        return loanMapper.mapToLoanDTO(loan);
    }

    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loan> optionalLoan = loansRepository.findByMobileNumber(mobileNumber);

        if (optionalLoan.isPresent()) {
            throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber " + mobileNumber);
        }

        long number = 1000000000L + new Random().nextLong(9000000000L);

        Loan loan  = new Loan(mobileNumber, Long.toString(number), "HOUSE", 100000, 1000, 99000);

        loansRepository.save(loan);
    }

    @Override
    public void updateLoan(LoanDTO loanDTO) {
        Loan loan = loansRepository.findByLoanNumber(loanDTO.loanNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "loanNumber", loanDTO.loanNumber()));

        loanMapper.mapToLoan(loanDTO, loan);

        loansRepository.save(loan);
    }

    @Override
    public void deleteLoan(String mobileNumber) {
        Loan loan = getExistentLoan(mobileNumber);

        loansRepository.deleteById(loan.getId());
    }

    private Loan getExistentLoan(String mobileNumber) {
        return loansRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber));
    }


}
