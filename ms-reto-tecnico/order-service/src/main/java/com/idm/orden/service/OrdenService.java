package com.idm.orden.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.idm.orden.dto.OrderView;
import com.idm.orden.dto.ProductoDto;
import com.idm.orden.dto.RequestDto;
import com.idm.orden.model.Orden;
import com.idm.orden.repository.OrdenRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrdenService {

	@Autowired
	private WebClient webClient;

	@Autowired
	private OrdenRepository ordenRepository;

	public OrdenService(WebClient webClient, OrdenRepository ordenRepository) {
		this.webClient = webClient;
		this.ordenRepository = ordenRepository;
	}

	public Flux<Orden> listarOrden() {
		return ordenRepository.findAll();
	}

	public Mono<Orden> listarOrdenPorID(Long id) {
		return ordenRepository.findById(id);
	}

	public Mono<OrderView> crearOrden(RequestDto req) {
		return webClient.get().uri("/{id}", req.productoId()).retrieve().bodyToMono(ProductoDto.class)
				.flatMap(p -> ordenRepository.save(new Orden(null, req.productoId(), req.cantidad()))
						.map(saved -> new OrderView(saved.getId(), saved.getProductoId(), saved.getCantidad(),
								p.nombre(), p.precio(), p.precio() * saved.getCantidad())));
	}

}
