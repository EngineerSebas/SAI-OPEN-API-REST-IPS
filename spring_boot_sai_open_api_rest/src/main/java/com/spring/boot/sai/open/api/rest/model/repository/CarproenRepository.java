package com.spring.boot.sai.open.api.rest.model.repository;

import com.spring.boot.sai.open.api.rest.model.entity.Carproen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarproenRepository extends JpaRepository<Carproen, Long> {

    @Query(value = "SELECT TIPO,BATCH FROM CARPROEN WHERE TIPO=:TIPO AND BATCH = :BATCH", nativeQuery = true)
    Object findCarproenByTipoAndBatchExample(@Param("TIPO") String TIPO, @Param("BATCH") Integer BATCH);
}
