package com.spring.boot.sai.open.api.rest.dto;

import com.spring.boot.sai.open.api.rest.model.entity.Partner;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PartnerResponse {
    private int count;
    private List<Partner> results;

}

