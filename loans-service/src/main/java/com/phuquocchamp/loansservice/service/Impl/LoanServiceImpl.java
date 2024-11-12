package com.phuquocchamp.loansservice.service.Impl;

import com.phuquocchamp.loansservice.constant.LoanConstant;
import com.phuquocchamp.loansservice.dto.LoanDto;
import com.phuquocchamp.loansservice.entity.Loan;
import com.phuquocchamp.loansservice.exception.LoanAlreadyExistsException;
import com.phuquocchamp.loansservice.exception.ResourceNotFoundException;
import com.phuquocchamp.loansservice.mapper.LoanMapper;
import com.phuquocchamp.loansservice.repository.LoanRepository;
import com.phuquocchamp.loansservice.service.ILoanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements ILoanService {

    private LoanRepository loanRepository;

    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loan> optionalLoan = loanRepository.findByMobileNumber(mobileNumber);
        if(optionalLoan.isPresent()){
            throw new LoanAlreadyExistsException("LOAN ALREADY REGISTERED WITH THIS MOBILE NUMBER : " + mobileNumber );
        }
        loanRepository.save(createNewLoan(mobileNumber));
    }

    private Loan createNewLoan(String mobileNumber) {
        Loan newLoan = new Loan();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoanConstant.HOME_LOAN);
        newLoan.setTotalLoan(LoanConstant.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoanConstant.NEW_LOAN_LIMIT);
        return newLoan;
    }
    @Override
    public LoanDto fetchLoan(String mobileNumber) {
        Loan loan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("LOAN", "MOBILE NUMBER", mobileNumber)
        );
        return LoanMapper.mapToLoanDto(loan, new LoanDto());
    }

    @Override
    public boolean updateLoan(LoanDto loanDto) {
        Loan loan = loanRepository.findByLoanNumber(loanDto.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("LOAN", "LOAN NUMBER", loanDto.getLoanNumber())
        );
        LoanMapper.mapToLoan(loanDto, loan);
        loanRepository.save(loan);
        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loan loan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("LOAN", "MOBILE NUMBER", mobileNumber)
        );
        loanRepository.deleteById(loan.getLoanId());
        return true;
    }
}