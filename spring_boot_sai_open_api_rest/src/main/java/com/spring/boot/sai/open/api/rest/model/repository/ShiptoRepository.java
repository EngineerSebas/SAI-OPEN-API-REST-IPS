package com.spring.boot.sai.open.api.rest.model.repository;


import com.spring.boot.sai.open.api.rest.model.entity.Shipto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiptoRepository extends JpaRepository<Shipto, Integer> {
}
