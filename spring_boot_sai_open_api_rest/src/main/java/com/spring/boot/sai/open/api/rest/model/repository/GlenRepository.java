package com.spring.boot.sai.open.api.rest.model.repository;

import com.spring.boot.sai.open.api.rest.model.entity.Glen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;

@Repository
public interface GlenRepository extends JpaRepository<Glen, Long> {

    @Query(value = "SELECT TIPO,BATCH FROM GLEN WHERE TIPO=:TIPO AND BATCH = :BATCH", nativeQuery = true)
    Object findGlenByTipoAndBatchExample(@Param("TIPO") String TIPO, @Param("BATCH") Integer BATCH);


    @Query(value = "INSERT INTO GLEN (E, S, TIPO, BATCH, FECHA, USERNAME, REVISADO, REVISOR, FECHA_REVISION, EXPORTADA, ESTADO, DESCRIPCION, ID_N) VALUES (1, 1, :TIPO, :BATCH,:FECHA, 'SAI', 'N', NULL, NULL, 'N', NULL, NULL, NULL)", nativeQuery = true)
    void insertGlen(@Param("TIPO") String tipo, @Param("BATCH") String batch, @Param("FECHA") Date fecha);

}
