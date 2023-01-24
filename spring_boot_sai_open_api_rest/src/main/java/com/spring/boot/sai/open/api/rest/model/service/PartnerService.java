package com.spring.boot.sai.open.api.rest.model.service;

import com.spring.boot.sai.open.api.rest.context.Filters;
import com.spring.boot.sai.open.api.rest.dto.PartnerResponse;

public interface PartnerService {
    public PartnerResponse getPartners(Filters partnerRequest);
}
