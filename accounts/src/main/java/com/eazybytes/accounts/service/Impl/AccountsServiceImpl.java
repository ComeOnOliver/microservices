package com.eazybytes.accounts.service.Impl;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.AccountsDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exceptions.CannotFind;
import com.eazybytes.accounts.exceptions.CustomerAleareadyExisted;
import com.eazybytes.accounts.mapper.AccountsMapper;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.service.IAccountsService;

import lombok.AllArgsConstructor;

import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService{

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public CustomerDto getCustomer(String customerId) {
        // TODO Auto-generated method stub
        Customer customerData = new Customer();
        Long Id = Long.valueOf(customerId); // Convert customerId to Long
        
        customerRepository.findByCustomerId(Id).ifPresent(customer -> {
            customerData.setEmail(customer.getEmail());
            customerData.setMobileNumber(customer.getMobileNumber());
            customerData.setName(customer.getName());
        });

        Accounts accountData = new Accounts();
        Optional<Accounts> optionalAccount = accountsRepository.findByCustomerId(Id);
        if (optionalAccount.isPresent()) {
            Accounts account = optionalAccount.get();
            accountData.setAccountNumber(account.getAccountNumber());
            accountData.setAccountType(account.getAccountType());   
            accountData.setBranchAddress(account.getBranchAddress());
        } else {
            throw new CannotFind("Account not found");
        }

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customerData, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accountData, new AccountsDto()));
        return customerDto;
    }


    @Override
    public void createAccount(CustomerDto customerDto) {
       Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
       customerRepository.findByMobileNumber(customer.getMobileNumber()).ifPresent(cust -> {
           throw new CustomerAleareadyExisted("Customer already exists");
       });
       customer.setCreatedAt(LocalDateTime.now());
       customer.setCreatedBy("Admin");
       customer.setUpdatedAt(LocalDateTime.now());
        customer.setUpdatedBy("Admin");
       Customer savedCustomer = customerRepository.save(customer);
       accountsRepository.save(createNewAccount(savedCustomer));

    }

        /**
     * @param customer - Customer Object
     * @return the new account details
     */
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
       newAccount.setCreatedBy("Admin");
       newAccount.setUpdatedAt(LocalDateTime.now());
        newAccount.setUpdatedBy("Admin");
        return newAccount;
    }
}
