package com.spring.boot.sai.open.api.rest.model.service.impl;

import com.spring.boot.sai.open.api.rest.context.Filters;
import com.spring.boot.sai.open.api.rest.dto.CreatePartnerRequest;
import com.spring.boot.sai.open.api.rest.dto.CreatePartnerResponse;
import com.spring.boot.sai.open.api.rest.dto.PartnerResponse;
import com.spring.boot.sai.open.api.rest.model.entity.Cust;
import com.spring.boot.sai.open.api.rest.model.entity.Partner;
import com.spring.boot.sai.open.api.rest.model.repository.CustRepository;
import com.spring.boot.sai.open.api.rest.model.repository.PartnerRepository;
import com.spring.boot.sai.open.api.rest.model.service.PartnerService;
import com.spring.boot.sai.open.api.rest.util.CedulaVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PartnerServiceImpl implements PartnerService {
    @Autowired
    private PartnerRepository partnerRepository;
    @Autowired
    private CustRepository custRepository;

    @Override
    public PartnerResponse getPartners(Filters partnerRequest) {
        List<String> filter = partnerRequest.getFilters().get(0);
        String xidentificationFilter = filter.get(2).replace("\"", "'");
        Partner partner = partnerRepository.findPartnerByCode(xidentificationFilter);
        PartnerResponse partnerResponse = new PartnerResponse();
        partnerResponse.setCount(1);
        List<Partner> partners = new ArrayList<>();
        partners.add(partner);
        partnerResponse.setResults(partners);
        return partnerResponse;
    }

    @Override
    public CreatePartnerResponse createPartner(CreatePartnerRequest createPartnerRequest) {
        String countryName = getCountryName(createPartnerRequest.getLang());
        String nitDv = getNitDv(createPartnerRequest.getXidentification());

        Cust cust = new Cust();
        cust.setId_n(createPartnerRequest.getXidentification());
        cust.setCompany(createPartnerRequest.getName());
        cust.setCompany_extendidox(createPartnerRequest.getCompanyname());
        cust.setAddr1(createPartnerRequest.getStreet());
        cust.setPhone1(createPartnerRequest.getPhone());
        cust.setPhone2(createPartnerRequest.getMobile());
        cust.setPais(countryName.toUpperCase());
        cust.setCreditlmt(createPartnerRequest.getCredit_limit());
        cust.setMedico(getBooleanValue(createPartnerRequest.getIs_doctor()));
        cust.setCliente(getBooleanValue(createPartnerRequest.getCustomer()));
        cust.setProveedor(getBooleanValue(createPartnerRequest.getSupplier()));
        cust.setCity(createPartnerRequest.getXcity().toUpperCase());
        cust.setNit(createPartnerRequest.getXidentification() + "-" + nitDv);
        cust.setId_tipocartera("CC");
        custRepository.save(cust);

        CreatePartnerResponse createPartnerResponse = new CreatePartnerResponse();
        createPartnerResponse.setId(createPartnerRequest.getXidentification());
        return createPartnerResponse;
    }
    private String getCountryName(String lang) {
        switch (lang) {
            case "es_CO":
                return "Colombia";
            default:
                return "Desconocido";
        }
    }
    private String getNitDv(String xIdentification) {
        return String.valueOf(CedulaVerification.generarDv(Long.parseLong(xIdentification)));
    }
    private String getBooleanValue(boolean value) {
        return value ? "True" : "False";
    }

}
