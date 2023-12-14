
package com.eazybytes.accounts.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ErrorDto;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.service.IAccountsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
@Tag(name = "CURD REST Apis for Accounts", description = "Details of The Accounts API")
public class AccountsController {

    private IAccountsService iAccountsService;

    @Operation(summary = "Create a new account", description = "Create a new account", tags = { "Accounts" })
    @ApiResponse(responseCode = "201", description = "Account created successfully")
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {

        iAccountsService.createAccount(customerDto);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @GetMapping("/fetchcustomer")
    public ResponseEntity<CustomerDto> getCustomer(@Valid @RequestParam

                                                String customerId) {

        CustomerDto customerDto = iAccountsService.getCustomer(customerId);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(customerDto);
    }

    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Customer updated successfully"),
        @ApiResponse(
            responseCode = "417", 
            description = "Customer update failed", 
            content = @Content(schema = @Schema(implementation = ErrorDto.class)))})
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCustomer(@RequestBody CustomerDto customerDto) {

        boolean isUpdated = iAccountsService.updateCustomer(customerDto);
        if (isUpdated) {
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
    }
}
