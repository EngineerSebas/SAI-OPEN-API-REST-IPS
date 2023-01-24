package com.spring.boot.sai.open.api.rest.controller;

import com.spring.boot.sai.open.api.rest.context.Filters;
import com.spring.boot.sai.open.api.rest.dto.PartnerResponse;
import com.spring.boot.sai.open.api.rest.model.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class PartnerController {

    @Autowired
    private PartnerService partnerService;

    @PostMapping("/res.partner")
    public ResponseEntity<PartnerResponse> getPartnerById(@RequestBody Filters partnerRequest) {
        PartnerResponse partnerResponse = partnerService.getPartners(partnerRequest);
        return ResponseEntity.ok(partnerResponse);
    }

}

