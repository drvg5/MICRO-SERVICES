package com.microstart.accounts.service.impl;

import com.microstart.accounts.constants.AccountsConstants;
import com.microstart.accounts.dto.AccountsDto;
import com.microstart.accounts.dto.CustomerDto;
import com.microstart.accounts.entity.Accounts;
import com.microstart.accounts.entity.Customer;
import com.microstart.accounts.exception.CustomerAlreadyExistsException;
import com.microstart.accounts.exception.ResourceNotFoundException;
import com.microstart.accounts.mapper.AccountsMapper;
import com.microstart.accounts.mapper.CustomerMapper;
import com.microstart.accounts.repository.AccountsRepository;
import com.microstart.accounts.repository.CustomerRepository;
import com.microstart.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
       Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
       Optional<Customer> customerCheckByMob = customerRepository.findByMobileNumber(customer.getMobileNumber());
       if(customerCheckByMob.isPresent()){
           throw new CustomerAlreadyExistsException("Customer with Mobile Number: " + customer.getMobileNumber() + " already exists");
       }
       Customer savedCustomerObject = customerRepository.save(customer);
       accountsRepository.save(createNewAccount(savedCustomerObject));
 
    }

    private Accounts createNewAccount(Customer savedCustomerObject) {
        Accounts account = new Accounts();
        account.setCustomerId(savedCustomerObject.getCustomerId());

        //create account number
        long accNo = 1000000000L + new Random().nextInt(900000000);
        account.setAccountNumber(accNo);
        account.setAccountType(AccountsConstants.SAVINGS);
        account.setBranchAddress(AccountsConstants.ADDRESS);

        return account;
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber){
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(account, new AccountsDto()));
        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isRecordUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto!=null){
            Accounts existingAcccount = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "accountNumber", accountsDto.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccounts(accountsDto, existingAcccount);
            Accounts accounts = accountsRepository.save(existingAcccount);

            Long customerId = accounts.getCustomerId();
            Customer existingCustomer = customerRepository.findById(accounts.getCustomerId()).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "customerId", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto, existingCustomer);
            customerRepository.save(existingCustomer);
            isRecordUpdated = true;
        }
        return isRecordUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountsRepository.findByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }
}
