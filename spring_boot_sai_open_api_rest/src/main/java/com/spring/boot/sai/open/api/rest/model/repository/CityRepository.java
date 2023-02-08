package com.spring.boot.sai.open.api.rest.model.repository;

import com.spring.boot.sai.open.api.rest.model.entity.City;
import com.spring.boot.sai.open.api.rest.model.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    @Query(value = "SELECT 1 AS id, CIUDADES.ciudad AS name, CIUDADES.codigo AS code, CIUDADES.id_depto AS state_id, 169 AS country_id FROM CIUDADES where CIUDADES.codigo=:code", nativeQuery = true)
    City findCityByCode(@Param("code") String code);

}

