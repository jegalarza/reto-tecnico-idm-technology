package com.idm.orden.controller;

import com.idm.orden.dto.OrderView;
import com.idm.orden.dto.RequestDto;
import com.idm.orden.model.Orden;
import com.idm.orden.service.OrdenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path="/api/ordenes", produces=MediaType.APPLICATION_JSON_VALUE)
public class OrdenController {

	@Autowired
    private OrdenService ordenService;
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<OrderView>> crearOrden(@RequestBody RequestDto request) {
    	return ordenService.crearOrden(request)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().build()));
    }

    @GetMapping
    public Mono<ResponseEntity<Flux<Orden>>> listarOrden() {
        return ordenService.listarOrden()
            .collectList()
            .flatMap(list -> {
                if (list.isEmpty()) {
                    return Mono.just(ResponseEntity.notFound().build());
                } else {
                    return Mono.just(ResponseEntity.ok(Flux.fromIterable(list)));
                }
            });
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Orden>> listarOrdenPorID(@PathVariable Long id) {
        return ordenService.listarOrdenPorID(id)
            .map(ResponseEntity::ok)
            .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

}
