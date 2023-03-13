package com.spring.boot.sai.open.api.rest.model.service;

import com.spring.boot.sai.open.api.rest.dto.AccountingEntryResponse;

public interface AccountingService {
    AccountingEntryResponse getAccountingEntryResponse(String clase, Integer bash);
}
