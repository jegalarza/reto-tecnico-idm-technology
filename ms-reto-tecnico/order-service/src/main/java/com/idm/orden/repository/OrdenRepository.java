package com.idm.orden.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.idm.orden.model.Orden;

@Repository
public interface OrdenRepository extends ReactiveCrudRepository<Orden, Long> {}
