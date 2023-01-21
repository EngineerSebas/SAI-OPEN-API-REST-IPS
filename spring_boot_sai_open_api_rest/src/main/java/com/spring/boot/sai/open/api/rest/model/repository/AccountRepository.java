package com.spring.boot.sai.open.api.rest.model.repository;

import com.spring.boot.sai.open.api.rest.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query(value = "SELECT acct as code, descripcion as name,1 as id FROM acct WHERE acct > 1000000 ORDER BY acct", nativeQuery = true)
    List<Account> findAllAccounts();

    @Query(value = "select codigo as code,descripcion as name, 1 as id from lista where tipo='CC' and DPCCEST ='Abierto'", nativeQuery = true)
    List<Account> findAllAccountsAnalyticCost();
}