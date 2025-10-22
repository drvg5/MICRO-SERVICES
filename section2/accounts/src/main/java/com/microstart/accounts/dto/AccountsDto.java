package com.microstart.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Schema(name = "Accounts",
        description = "Schema for Account details")
public class AccountsDto {
    @Schema(description = "Account Number of the Customer",
            example = "1234567890")
    @NotEmpty(message = "Account number cannot be empty")
    @Pattern(regexp = "^[0-9]{10,18}$", message = "Account number must be between 10 and 18 digits")
    private Long accountNumber;

    @Schema(description = "Type of the Account",
            example = "Savings")
    @NotEmpty(message = "Account type cannot be empty")
    private String accountType;

    @Schema(description = "Branch Address of the Account",
            example = "123 Main St, Cityville")
    @NotEmpty(message = "Branch address cannot be empty")
    private String branchAddress;
}
