package com.spring.boot.sai.open.api.rest.dto;

import com.spring.boot.sai.open.api.rest.model.entity.City;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CityResponse {
    private int count;
    private List<City> results;

}

