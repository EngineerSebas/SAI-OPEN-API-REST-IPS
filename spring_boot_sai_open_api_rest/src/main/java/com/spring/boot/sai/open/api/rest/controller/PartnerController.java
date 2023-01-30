package com.spring.boot.sai.open.api.rest.controller;

import com.spring.boot.sai.open.api.rest.auth.JwtUtil;
import com.spring.boot.sai.open.api.rest.context.Filters;
import com.spring.boot.sai.open.api.rest.dto.PartnerResponse;
import com.spring.boot.sai.open.api.rest.model.entity.Cust;
import com.spring.boot.sai.open.api.rest.model.repository.CustRepository;
import com.spring.boot.sai.open.api.rest.model.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            return ResponseEntity.ok(partnerResponse);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Autowired
    private CustRepository custRepository;

    @PostMapping("/res.partner/test")
    public ResponseEntity<?> createPartner(@RequestBody Cust custRequest) {
            Cust custResponse = custRepository.save(custRequest);
            return ResponseEntity.ok(custResponse);
    }
}

