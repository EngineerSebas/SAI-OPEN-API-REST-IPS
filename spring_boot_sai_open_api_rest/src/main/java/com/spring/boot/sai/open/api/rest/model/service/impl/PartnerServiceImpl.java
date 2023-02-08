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
        cust.setIDVEND(99);
        cust.setCompany(createPartnerRequest.getName());
        cust.setCompany_extendido(createPartnerRequest.getCompanyname());
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
        cust.setRegimen("48");
        cust.setId_tipocartera("CC");
        cust.setInactivo("N");
        cust.setZona((short) 2);
        custRepository.save(cust);



        Shipto shipto = new Shipto();
        shipto.setId_n(createPartnerRequest.getXidentification());
        shipto.setSuccliente(0);
        shipto.setIDVEND(99);
        shipto.setCompany(createPartnerRequest.getName());
        shipto.setPrimer_nombre(getFirstName((int) createPartnerRequest.getDoctype().shortValue(),createPartnerRequest));
        shipto.setSegundo_nombre(createPartnerRequest.getX_name2());
        shipto.setPrimer_apellido(createPartnerRequest.getX_lastname1());
        shipto.setSegundo_apellido(createPartnerRequest.getX_lastname2());
        shipto.setCompany_extendido(createPartnerRequest.getCompanyname());
        shipto.setAddr1(createPartnerRequest.getStreet());
        shipto.setPhone1(createPartnerRequest.getPhone());
        shipto.setPhone2(createPartnerRequest.getMobile());
        shipto.setEmail(createPartnerRequest.getEmail());
        shipto.setEmail_fac_elec(createPartnerRequest.getEmail());
        shipto.setCreditlmt(createPartnerRequest.getCredit_limit());
        shipto.setComment1(createPartnerRequest.getComment());
        shipto.setCity(createPartnerRequest.getXcity().toUpperCase());
        shiptoRepository.save(shipto);

        Tributaria tributaria = new Tributaria();
        tributaria.setId_n(createPartnerRequest.getXidentification());
        tributaria.setCompany(createPartnerRequest.getName());
        tributaria.setPrimer_nombre(getFirstName((int) createPartnerRequest.getDoctype().shortValue(), createPartnerRequest));
        tributaria.setSegundo_nombre(createPartnerRequest.getX_name2());
        tributaria.setPrimer_apellido(createPartnerRequest.getX_lastname1());
        tributaria.setSegundo_apellido(createPartnerRequest.getX_lastname2());
        tributaria.setTipo_contribuyente(createPartnerRequest.getPersontype().shortValue());
        System.out.println("Tipo contribuyente: " + createPartnerRequest.getPersontype().shortValue());
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

