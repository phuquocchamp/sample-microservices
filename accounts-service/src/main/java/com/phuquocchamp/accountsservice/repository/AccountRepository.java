package com.phuquocchamp.accountsservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.phuquocchamp.accountsservice.entity.Account;

import jakarta.transaction.Transactional;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    
    /**
     * 
     * @param customerId
     * @return
     */
    Optional<Account> findByCustomerId(Long customerId);


    /**
     * 
     * @param accountNumber
     * @return 
     */
    Optional<Account> findByAccountNumber(Long accountNumber);


    @Transactional
    @Modifying
    void deleteByCustomerId(Long customerId);
}
  