package com.spring.boot.sai.open.api.rest.dto;

import com.spring.boot.sai.open.api.rest.model.entity.Account;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class AccountResponse {
    private int count;
    private List<Account> results;


}
