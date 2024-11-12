package com.phuquocchamp.accountsservice.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.phuquocchamp.accountsservice.constant.AccountConstant;
import com.phuquocchamp.accountsservice.dto.AccountDto;
import com.phuquocchamp.accountsservice.dto.CustomerDto;
import com.phuquocchamp.accountsservice.entity.Account;
import com.phuquocchamp.accountsservice.entity.Customer;
import com.phuquocchamp.accountsservice.exception.CustomerAlreadyExistsException;
import com.phuquocchamp.accountsservice.exception.ResourceNotFoundException;
import com.phuquocchamp.accountsservice.mapper.AccountMapper;
import com.phuquocchamp.accountsservice.mapper.CustomerMapper;
import com.phuquocchamp.accountsservice.repository.AccountRepository;
import com.phuquocchamp.accountsservice.repository.CustomerRepository;
import com.phuquocchamp.accountsservice.service.IAccountService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    /**
     * Create account
     * @param customerDto
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException(
                    "Customer already exists with mobile number: "
                    + customerDto.getMobileNumber()
            );
        }

        Customer savedCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));
    }

    /**
     * Update account details
     * @param customerDto
     */
    private Account createNewAccount(Customer customer) {
        Account newAccount = new Account();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountConstant.SAVINGS);
        newAccount.setBranchAddress(AccountConstant.ADDRESS);
        return newAccount;
    }
    

    /**
     * Fetch account details
     * @param mobileNumber
     * @return
     */
    @Override
    public CustomerDto fetchAccount(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("CUSTOMER", "MOBILE_NUMBER", mobileNumber));

        Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("ACCOUNT", "CUSTOMER_ID", customer.getCustomerId().toString()));

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccount(AccountMapper.mapToAccountDto(account, new AccountDto()));

        return customerDto;
    }
    
    /**
    * Update account details
    * @param customerDto
    */
    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountDto accountDto = customerDto.getAccount();
        if (accountDto != null) {
            Account account = accountRepository.findById(accountDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("ACCOUNT", "ACCOUNT_NUMBER",
                            accountDto.getAccountNumber().toString()));
            AccountMapper.mapToAccount(accountDto, account);
            account = accountRepository.save(account);

            Long customerId = account.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("CUSTOMER", "CUSTOMER_ID", customerId.toString()));
            CustomerMapper.mapToCustomer(customerDto, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }
    

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
            () -> new ResourceNotFoundException("CUSTOMER", "MOBILE_NUMBER", mobileNumber)
        );
        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());

        return true;
    }
}
