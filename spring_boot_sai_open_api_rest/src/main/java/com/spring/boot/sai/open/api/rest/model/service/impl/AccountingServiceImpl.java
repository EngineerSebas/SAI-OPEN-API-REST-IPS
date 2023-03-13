package com.spring.boot.sai.open.api.rest.model.service.impl;

import com.spring.boot.sai.open.api.rest.dto.AccountingEntryRequest;
import com.spring.boot.sai.open.api.rest.dto.AccountingEntryResponse;
import com.spring.boot.sai.open.api.rest.model.repository.CarproenRepository;
import com.spring.boot.sai.open.api.rest.model.repository.GlenRepository;
import com.spring.boot.sai.open.api.rest.model.repository.TipDocRepository;
import com.spring.boot.sai.open.api.rest.model.service.AccountingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AccountingServiceImpl implements AccountingService {
    @Autowired
    private TipDocRepository tipDocRepository;
    @Autowired
    private GlenRepository glenRepository;
    @Autowired
    private CarproenRepository carproenRepository;

    @Override
    public AccountingEntryResponse getAccountingEntryResponse(String clase, Integer bash) {
        String obteinTipDocByClass = tipDocRepository.findTipDocByClase(clase);
        AccountingEntryRequest accountingEntryRequest = new AccountingEntryRequest();
        List<AccountingEntryRequest> accountingEntryRequests = new ArrayList<>();
        AccountingEntryResponse accountingEntryResponse = new AccountingEntryResponse();
        if (Objects.equals(obteinTipDocByClass, "CC")) {
            Object result = glenRepository.findGlenByTipoAndBatchExample(obteinTipDocByClass, bash);
            if (result instanceof Object[]) {
                Object[] row = (Object[]) result;
                Integer batch = (Integer) row[1];
                accountingEntryRequest.setId(1);
                accountingEntryRequest.setName(clase + "/" + batch);
                accountingEntryRequests.add(accountingEntryRequest);
                accountingEntryResponse.setCount(1);
                accountingEntryResponse.setResults(accountingEntryRequests);
                return accountingEntryResponse;
            }
        } else if (Objects.equals(obteinTipDocByClass, "FP")) {
            Object result = carproenRepository.findCarproenByTipoAndBatchExample(obteinTipDocByClass, bash);
            if (result instanceof Object[]) {
                Object[] row = (Object[]) result;
                Integer batch = (Integer) row[1];
                accountingEntryRequest.setId(1);
                accountingEntryRequest.setName(clase + "/" + batch);
                accountingEntryRequests.add(accountingEntryRequest);
                accountingEntryResponse.setCount(1);
                accountingEntryResponse.setResults(accountingEntryRequests);
                return accountingEntryResponse;
            }
        }
        return null;
    }
}
