package com.spring.boot.sai.open.api.rest.controller;

import com.spring.boot.sai.open.api.rest.auth.JwtUtil;
import com.spring.boot.sai.open.api.rest.context.Filters;
import com.spring.boot.sai.open.api.rest.dto.CityResponse;
import com.spring.boot.sai.open.api.rest.model.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class CityController {

    @Autowired
    private CityService cityService;
    @Autowired
    private JwtUtil jwtUtil;
    @GetMapping("/res.country.state.city")
    public ResponseEntity<CityResponse> getCityByCode(@RequestHeader("Access-Token") String token,@RequestBody Filters cityRequest) {
        if (!jwtUtil.validateToken(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {
            CityResponse cityResponse = cityService.getCityByCode(cityRequest);
            return ResponseEntity.ok(cityResponse);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

