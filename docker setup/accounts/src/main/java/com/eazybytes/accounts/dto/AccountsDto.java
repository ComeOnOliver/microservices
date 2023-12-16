package com.eazybytes.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


@Data
public class AccountsDto {
    
    @NotEmpty(message = "Account number cannot be empty")
    private Long accountNumber;

    @NotEmpty(message = "Account type cannot be empty")
    private String accountType;

    @NotEmpty(message = "Branch address cannot be empty")
    private String branchAddress;
}
