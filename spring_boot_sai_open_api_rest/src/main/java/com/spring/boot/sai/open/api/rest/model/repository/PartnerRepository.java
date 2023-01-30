package com.spring.boot.sai.open.api.rest.model.repository;

import com.spring.boot.sai.open.api.rest.model.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {
    @Query(value = "SELECT shipto.id_n as xidentification, shipto.company as name, 1 as id from shipto where shipto.id_n=:id_n  ", nativeQuery = true)
    Partner findPartnerByCode(@Param("id_n") String id_n);

}

