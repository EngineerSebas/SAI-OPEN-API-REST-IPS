package com.spring.boot.sai.open.api.rest.model.service.impl;

import com.spring.boot.sai.open.api.rest.context.Filters;
import com.spring.boot.sai.open.api.rest.dto.CityResponse;
import com.spring.boot.sai.open.api.rest.dto.PartnerResponse;
import com.spring.boot.sai.open.api.rest.model.entity.City;
import com.spring.boot.sai.open.api.rest.model.entity.Partner;
import com.spring.boot.sai.open.api.rest.model.repository.CityRepository;
import com.spring.boot.sai.open.api.rest.model.repository.PartnerRepository;
import com.spring.boot.sai.open.api.rest.model.service.CityService;
import com.spring.boot.sai.open.api.rest.model.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepository cityRepository;


    @Override
    public CityResponse getCityByCode(Filters cityRequest) {
        List<String> filter = cityRequest.getFilters().get(0);
        String codeCityFilter = filter.get(2).replace("\"", "'");
        City city = cityRepository.findCityByCode(codeCityFilter);
        CityResponse cityResponse = new CityResponse();
        cityResponse.setCount(1);
        List<City> cities = new ArrayList<>();
        cities.add(city);
        cityResponse.setResults(cities);
        return cityResponse;
    }
}
