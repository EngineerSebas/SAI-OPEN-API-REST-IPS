package com.spring.boot.sai.open.api.rest.model.service;

import com.spring.boot.sai.open.api.rest.model.entity.Account;

import java.util.List;

public interface AccountService {
    public List<Account> getAllAccounts();

    public List<Account> getAllAccountsAnalyticCost();
}