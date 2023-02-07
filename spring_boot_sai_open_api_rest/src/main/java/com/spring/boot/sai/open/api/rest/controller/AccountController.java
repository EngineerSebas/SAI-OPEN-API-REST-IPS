package com.spring.boot.sai.open.api.rest.controller;


import com.spring.boot.sai.open.api.rest.auth.JwtUtil;
import com.spring.boot.sai.open.api.rest.dto.AccountResponse;
import com.spring.boot.sai.open.api.rest.model.entity.Account;
import com.spring.boot.sai.open.api.rest.model.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("api")
public class AccountController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AccountService accountService;

    @GetMapping("/account.account")
    public ResponseEntity<AccountResponse> getAllAccounts(@RequestHeader("Access-Token") String token) {
        if (!jwtUtil.validateToken(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {
            List<Account> accounts = accountService.getAllAccounts();
            List<Account> newAccounts = new ArrayList<>();
            for (Account account : accounts) {
                String value = account.getCode();
                float floatValue = Float.parseFloat(value);
                String result = String.format(Locale.ROOT, "%.0f", floatValue);
                account.setCode(result);
                account.setName(account.getName().trim());
                newAccounts.add(account);
            }
            AccountResponse accountResponse = new AccountResponse();
            accountResponse.setCount(newAccounts.size());
            accountResponse.setResults(newAccounts);
            return ResponseEntity.ok(accountResponse);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/account.analytic.account")
    public ResponseEntity<AccountResponse> getAllAccountsAnalyticCost(@RequestHeader("Access-Token") String token) {
        if (!jwtUtil.validateToken(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {
            List<Account> accounts = accountService.getAllAccountsAnalyticCost();
            List<Account> newAccounts = new ArrayList<>();
            for (Account account : accounts) {
                account.setName(account.getName().trim());
                newAccounts.add(account);
            }
            AccountResponse accountResponse = new AccountResponse();
            accountResponse.setCount(newAccounts.size());
            accountResponse.setResults(newAccounts);
            return ResponseEntity.ok(accountResponse);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
