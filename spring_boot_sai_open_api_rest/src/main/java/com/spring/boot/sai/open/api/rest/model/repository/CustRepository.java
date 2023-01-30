package com.spring.boot.sai.open.api.rest.model.repository;

import com.spring.boot.sai.open.api.rest.model.entity.Cust;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustRepository extends JpaRepository<Cust, Integer> {
}
