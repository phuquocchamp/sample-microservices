package com.phuquocchamp.accountsservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
    name = "Customer",
    description = "Schema to hold Customer and Account information"
)
public class CustomerDto {
    @Schema(
            description = "Name of the Customer",
            example = "John Doe"
    )
    @NotEmpty(message = "Customer name is not null or empty")
    @Size(min = 5, max = 30, message = "Customer name must be between 5 and 30 characters")
    private String name;

    @Schema(
            description = "Name of the Customer",
            example = "johndoe@gmail.com"
    )
    @NotEmpty(message = "Customer name is not null or empty")
    @Email(message = "Email address should be valid")
    private String email;

    @Schema(
            description = "Mobile number of the Customer",
            example = "1234567890"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be valid")
    private String mobileNumber;

    @Schema(
            description = "Account details information of the Customer"
    )
    private AccountDto account;
}
