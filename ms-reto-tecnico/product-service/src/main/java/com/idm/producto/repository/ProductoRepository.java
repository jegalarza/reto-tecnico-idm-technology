package com.idm.producto.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.product.model.Producto;

import reactor.core.publisher.Flux;

public interface ProductoRepository extends ReactiveCrudRepository<Producto, Long> {
    Flux<Producto> findByCategoriaIgnoreCase(String categoria);
}
