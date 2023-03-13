package com.spring.boot.sai.open.api.rest.model.service.impl;

import com.spring.boot.sai.open.api.rest.context.Filters;
import com.spring.boot.sai.open.api.rest.dto.CreatePartnerRequest;
import com.spring.boot.sai.open.api.rest.dto.CreatePartnerResponse;
import com.spring.boot.sai.open.api.rest.dto.PartnerResponse;
import com.spring.boot.sai.open.api.rest.model.entity.Cust;
import com.spring.boot.sai.open.api.rest.model.entity.Partner;
import com.spring.boot.sai.open.api.rest.model.entity.Shipto;
import com.spring.boot.sai.open.api.rest.model.entity.Tributaria;
import com.spring.boot.sai.open.api.rest.model.repository.CustRepository;
import com.spring.boot.sai.open.api.rest.model.repository.PartnerRepository;
import com.spring.boot.sai.open.api.rest.model.repository.ShiptoRepository;
import com.spring.boot.sai.open.api.rest.model.repository.TributariaRepository;
import com.spring.boot.sai.open.api.rest.model.repository.impl.ChairAccountRepositoryImpl;
import com.spring.boot.sai.open.api.rest.model.service.PartnerService;
import com.spring.boot.sai.open.api.rest.util.CedulaVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PartnerServiceImpl implements PartnerService {
    @Autowired
    private PartnerRepository partnerRepository;
    @Autowired
    private CustRepository custRepository;
    @Autowired
    private ShiptoRepository shiptoRepository;
    @Autowired
    private TributariaRepository tributariaRepository;

    @Autowired
    private ChairAccountRepositoryImpl chairAccountRepository;

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

        chairAccountRepository.insertarCust(createPartnerRequest.getXidentification(),createPartnerRequest.getName(),createPartnerRequest.getCompanyname(),
                createPartnerRequest.getStreet(),createPartnerRequest.getPhone(),createPartnerRequest.getMobile(),countryName.toUpperCase(),getBooleanValue(createPartnerRequest.getIs_doctor()),
                createPartnerRequest.getCredit_limit(),getBooleanValue(createPartnerRequest.getCustomer()),getBooleanValue(createPartnerRequest.getSupplier()),
                createPartnerRequest.getXcity().toUpperCase(),createPartnerRequest.getXidentification() + "-" + nitDv,"CC","48","N", (short) 2, (short) 99);

        chairAccountRepository.insertarShipto(createPartnerRequest.getXidentification(),createPartnerRequest.getName(),createPartnerRequest.getCompanyname(),getFirstName((int) createPartnerRequest.getDoctype().shortValue(),createPartnerRequest),
                createPartnerRequest.getX_name2(),createPartnerRequest.getX_lastname1(),createPartnerRequest.getX_lastname2(),createPartnerRequest.getStreet(),createPartnerRequest.getPhone(),createPartnerRequest.getMobile(),
                createPartnerRequest.getEmail(),createPartnerRequest.getEmail(),createPartnerRequest.getCredit_limit(),createPartnerRequest.getComment(),createPartnerRequest.getXcity().toUpperCase(),0,99);

        Tributaria tributaria = new Tributaria();
        tributaria.setId_n(createPartnerRequest.getXidentification());
        tributaria.setCompany(createPartnerRequest.getName());
        tributaria.setPrimer_nombre(getFirstName((int) createPartnerRequest.getDoctype().shortValue(), createPartnerRequest));
        tributaria.setSegundo_nombre(createPartnerRequest.getX_name2());
        tributaria.setPrimer_apellido(createPartnerRequest.getX_lastname1());
        tributaria.setSegundo_apellido(createPartnerRequest.getX_lastname2());
        tributaria.setTipo_contribuyente(createPartnerRequest.getPersontype().shortValue());
        tributaria.setTdoc(createPartnerRequest.getDoctype().shortValue());
        tributariaRepository.save(tributaria);

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

    private String getFirstName(Integer doctype,CreatePartnerRequest createPartnerRequest){
        if (doctype == 31 || doctype == 22 || doctype == 21 || doctype == 42) {
            return null;
        } else {
            return createPartnerRequest.getX_name1();
        }
    }
}

