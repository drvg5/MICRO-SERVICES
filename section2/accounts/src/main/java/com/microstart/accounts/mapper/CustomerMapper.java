package com.microstart.accounts.mapper;

import com.microstart.accounts.dto.AccountsDto;
import com.microstart.accounts.dto.CustomerDto;
import com.microstart.accounts.entity.Accounts;
import com.microstart.accounts.entity.Customer;

public class CustomerMapper {

    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto){
        customerDto.setMobileNumber(customer.getMobileNumber());
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        return customerDto;
    }

    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer){
        customer.setEmail(customerDto.getEmail());
        customer.setName(customerDto.getName());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer ;
    }
}
