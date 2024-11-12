package com.phuquocchamp.accountsservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Schema(
        name = "Account",
        description = "Schema to hold Account Information"
)
@Data
public class AccountDto {
    @Schema(
            description = "Account Number",
            example = "1234567890"
    )
    @NotEmpty(message = "Account Number can not be a null or empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "Account Number must be 10 digits")
    private Long accountNumber;

    @Schema(
            description = "Account Type",
            example = "Savings"
    )
    @NotEmpty(message = "Account Type can not be a null or empty")
    private String accountType;

    @Schema(
            description = "Branch Address",
            example = "123 Main St, New York, NY 10001"
    )
    @NotEmpty(message = "Branch Address can not be a null or empty")
    private String branchAddress;
}
