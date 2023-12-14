package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Customer", description = "Schema to hold Customer and Account details")
public class CustomerDto {
    
    @Schema(description = "Customer Official Name", example = "Dezhang Wen")
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 5, message = "Name should have atleast 2 characters")
    private String name;

    @Schema(description = "Customer Email", example = "a@gmail.com")
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email should be valid format")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
    private String mobileNumber;

    
    private AccountsDto accountsDto;
}
