package com.spring.boot.sai.open.api.rest.controller;

import com.spring.boot.sai.open.api.rest.auth.JwtUtil;
import com.spring.boot.sai.open.api.rest.context.Filters;
import com.spring.boot.sai.open.api.rest.dto.CreatePartnerRequest;
import com.spring.boot.sai.open.api.rest.dto.CreatePartnerResponse;
import com.spring.boot.sai.open.api.rest.dto.PartnerResponse;
import com.spring.boot.sai.open.api.rest.model.entity.Partner;
import com.spring.boot.sai.open.api.rest.model.repository.TipDocRepository;
import com.spring.boot.sai.open.api.rest.model.service.PartnerService;
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
    @PostMapping("/res.partner")
    public ResponseEntity<CreatePartnerResponse> createPartner(@RequestHeader("Access-Token") String token,@RequestBody CreatePartnerRequest createPartnerRequest) {
        if (!jwtUtil.validateToken(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }try {
            CreatePartnerResponse createPartnerResponse = partnerService.createPartner(createPartnerRequest);
            return ResponseEntity.ok(createPartnerResponse);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

