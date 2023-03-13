package com.spring.boot.sai.open.api.rest.model.repository;

import com.spring.boot.sai.open.api.rest.model.entity.Acct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface AcctRepository extends JpaRepository<Acct, Long> {

    @Query(value = "SELECT TPOAPLCCION FROM ACCT WHERE ACCT = :ACCT", nativeQuery = true)
    String findAcctByAcct(@Param("ACCT") String ACCT);
}
