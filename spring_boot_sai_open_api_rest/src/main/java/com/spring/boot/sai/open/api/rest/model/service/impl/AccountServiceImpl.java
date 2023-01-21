package com.spring.boot.sai.open.api.rest.model.service.impl;

import java.util.List;

import com.spring.boot.sai.open.api.rest.dto.AccountResponse;
import com.spring.boot.sai.open.api.rest.model.entity.Account;
import com.spring.boot.sai.open.api.rest.model.repository.AccountRepository;
import com.spring.boot.sai.open.api.rest.model.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    private void incrementId(List<Account> accounts) {
        int counter = 1;
        for (Account account : accounts) {
            account.setId(counter);
            counter++;
        }
    }
    @Override
    public List<Account> getAllAccounts() {
        List<Account> accounts = accountRepository.findAllAccounts();
        incrementId(accounts);
        return accounts;
    }

    @Override
    public List<Account> getAllAccountsAnalyticCost() {
        List<Account> accounts = accountRepository.findAllAccountsAnalyticCost();
        incrementId(accounts);
        return accounts;
    }


}

