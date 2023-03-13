package com.spring.boot.sai.open.api.rest.model.repository;

import com.spring.boot.sai.open.api.rest.model.entity.Tipdoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TipDocRepository extends JpaRepository<Tipdoc, Long> {
    @Query(value = "SELECT TipDoc.TIPO  FROM TipDoc WHERE TipDoc.CLASE = :clase", nativeQuery = true)
    String findTipDocByClase(@Param("clase") String clase);
}
