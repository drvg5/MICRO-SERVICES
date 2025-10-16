package com.microstart.accounts.service;

import com.microstart.accounts.dto.CustomerDto;

public interface IAccountsService {
    void createAccount(CustomerDto customerDto);

    public CustomerDto fetchAccount(String mobileNumber);
}
