package com.phuquocchamp.loansservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Schema(
        name = "Loan",
        description = "Schema to hold Loan information"
)
@Data
public class LoanDto {
    @Schema(
            description = "Mobile Number of Customer", example = "4365327698"
    )
    @NotEmpty(message = "Mobile Number can not be null or empty")
    @Pattern(regexp = "^$|[0-9]{10}", message = "Mobile Number should be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Loan Number of the customer", example = "548732457654"
    )
    @NotEmpty(message = "Loan Number can not be null or empty")
    private String loanNumber;

    @Schema(
            description = "Type of the loan", example = "Home Loan"
    )
    @NotEmpty(message = "Loan Type can not be null or empty")
    private String loanType;

    @Positive(message = "Total loan amount should be greater than zero")
    @Schema(
            description = "Total loan amount", example = "100000"
    )
    private int totalLoan;

    @PositiveOrZero(message = "Total loan amount paid should be equal or greater than zero")
    @Schema(
            description = "Total loan amount paid", example = "1000"
    )
    private int amountPaid;

    @PositiveOrZero(message = "Total outstanding amount should be equal or greater than zero")
    @Schema(
            description = "Total outstanding amount against a loan", example = "99000"
    )
    private int outstandingAmount;
}
