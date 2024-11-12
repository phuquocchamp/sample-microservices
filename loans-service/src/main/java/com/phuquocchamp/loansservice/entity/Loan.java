package com.phuquocchamp.loansservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "loans")
@Getter @Setter
@ToString @NoArgsConstructor @AllArgsConstructor
public class Loan extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "load_id")
    private Long loanId;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "loan_number")
    private String loanNumber;

    @Column(name = "loan_type")
    private String loanType;

    @Column(name = "total_loan")
    private int totalLoan;

    @Column(name = "amount_paid")
    private int amountPaid;

    @Column(name = "outstanding_amount")
    private int outstandingAmount;
}
