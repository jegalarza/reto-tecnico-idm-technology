package com.idm.producto.controller;

import com.example.product.model.Producto;
import com.idm.producto.repository.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path="/api/productos", produces=MediaType.APPLICATION_JSON_VALUE)
public class ProductoController {

	@Autowired
    private ProductoRepository repo;

    
    @GetMapping
    public Mono<ResponseEntity<Flux<Producto>>> listar() {
        Flux<Producto> productos = repo.findAll();
        return productos.hasElements()
                .flatMap(existe -> existe
                        ? Mono.just(ResponseEntity.ok(productos))
                        : Mono.just(ResponseEntity.noContent().build()));
    }

    @GetMapping("/categoria/{categoria}")
    public Mono<ResponseEntity<Flux<Producto>>> all(@PathVariable String categoria) {
        Flux<Producto> productos = repo.findByCategoriaIgnoreCase(categoria);
        return productos.hasElements()
                .flatMap(existe -> existe
                        ? Mono.just(ResponseEntity.ok(productos))
                        : Mono.just(ResponseEntity.notFound().build()));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Producto>> byId(@PathVariable Long id) {
        return repo.findById(id)
                   .map(ResponseEntity::ok)
                   .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Producto>> create(@RequestBody Producto p) {
        return repo.save(p)
                   .map(saved -> ResponseEntity.status(HttpStatus.CREATED).body(saved));
    }
}
