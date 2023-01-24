package com.spring.boot.sai.open.api.rest.model.service.impl;

import com.spring.boot.sai.open.api.rest.context.Filters;
import com.spring.boot.sai.open.api.rest.dto.PartnerResponse;
import com.spring.boot.sai.open.api.rest.model.entity.Partner;
import com.spring.boot.sai.open.api.rest.model.repository.PartnerRepository;
import com.spring.boot.sai.open.api.rest.model.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PartnerServiceImpl implements PartnerService {
    @Autowired
    private PartnerRepository partnerRepository;

    @Override
    public PartnerResponse getPartners(Filters partnerRequest) {
        List<String> filter = partnerRequest.getFilters().get(0);
        Partner partner = partnerRepository.findShipto();
        PartnerResponse partnerResponse = new PartnerResponse();
        partnerResponse.setCount(1);
        List<Partner> partners = new ArrayList<>();
        partners.add(partner);
        partnerResponse.setResults(partners);
        return partnerResponse;
    }


}
