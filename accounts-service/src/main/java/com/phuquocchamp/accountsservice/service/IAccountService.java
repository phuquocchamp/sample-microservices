package com.phuquocchamp.accountsservice.service;

import com.phuquocchamp.accountsservice.dto.CustomerDto;

public interface IAccountService {
    /**
     * Create a new account
     * @param customerDto
     */
    void createAccount(CustomerDto customerDto);

    /**
     * Fetch account details
     * @param mobileNumber
     * @return
     */
    CustomerDto fetchAccount(String mobileNumber);

    /**
     * Update account details
     * @param customerDto
     * @return
     */
    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);
}
