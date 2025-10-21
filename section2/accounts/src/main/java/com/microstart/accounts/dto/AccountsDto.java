package com.microstart.accounts.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AccountsDto {
    @NotEmpty(message = "Account number cannot be empty")
    @Pattern(regexp = "^[0-9]{10,18}$", message = "Account number must be between 10 and 18 digits")
    private Long accountNumber;

    @NotEmpty(message = "Account type cannot be empty")
    private String accountType;

    @NotEmpty(message = "Branch address cannot be empty")
    private String branchAddress;
}
