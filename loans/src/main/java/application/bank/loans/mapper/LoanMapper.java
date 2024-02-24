package application.bank.loans.mapper;

import application.bank.loans.controller.dto.LoanDTO;
import application.bank.loans.repository.entity.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LoanMapper {

    LoanDTO mapToLoanDTO(Loan loan);

    Loan mapToLoan(LoanDTO loanDTO, @MappingTarget Loan loan);


}
