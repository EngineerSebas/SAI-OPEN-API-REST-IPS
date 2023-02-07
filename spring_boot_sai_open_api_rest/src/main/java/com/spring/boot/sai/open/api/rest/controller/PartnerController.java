package com.spring.boot.sai.open.api.rest.controller;

import com.spring.boot.sai.open.api.rest.auth.JwtUtil;
import com.spring.boot.sai.open.api.rest.context.Filters;
import com.spring.boot.sai.open.api.rest.dto.CreatePartnerRequest;
import com.spring.boot.sai.open.api.rest.dto.CreatePartnerResponse;
import com.spring.boot.sai.open.api.rest.dto.PartnerResponse;
import com.spring.boot.sai.open.api.rest.model.entity.Cust;
import com.spring.boot.sai.open.api.rest.model.entity.Partner;
import com.spring.boot.sai.open.api.rest.model.repository.CustRepository;
import com.spring.boot.sai.open.api.rest.model.repository.ShiptoRepository;
import com.spring.boot.sai.open.api.rest.model.repository.TributariaRepository;
import com.spring.boot.sai.open.api.rest.model.service.PartnerService;
import com.spring.boot.sai.open.api.rest.util.CedulaVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class PartnerController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PartnerService partnerService;

    @GetMapping("/res.partner")
    public ResponseEntity<PartnerResponse> getPartnerById(@RequestHeader("Access-Token") String token,@RequestBody Filters partnerRequest) {
        if (!jwtUtil.validateToken(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }try {
            PartnerResponse partnerResponse = partnerService.getPartners(partnerRequest);
            List<Partner> partners = partnerResponse.getResults();
            List<Partner> newPartners = new ArrayList<>();
            for (Partner partner : partners) {
                partner.setName(partner.getName().trim());
                partner.setXidentification(partner.getXidentification().trim());
                newPartners.add(partner);
            }
            partnerResponse.setResults(newPartners);
            return ResponseEntity.ok(partnerResponse);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/res.partner/test")
    public ResponseEntity<CreatePartnerResponse> createPartner(@RequestBody CreatePartnerRequest createPartnerRequest) {
        CreatePartnerResponse createPartnerResponse = partnerService.createPartner(createPartnerRequest);
        return ResponseEntity.ok(createPartnerResponse);
    }
}

