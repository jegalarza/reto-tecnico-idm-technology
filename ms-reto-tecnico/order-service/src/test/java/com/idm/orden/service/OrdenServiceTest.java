package com.idm.orden.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idm.orden.dto.OrderView;
import com.idm.orden.dto.ProductoDto;
import com.idm.orden.dto.RequestDto;
import com.idm.orden.model.Orden;
import com.idm.orden.repository.OrdenRepository;

import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.MockResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class OrdenServiceTest {

	private static MockWebServer mockWebServer;

	private ObjectMapper objectMapper = new ObjectMapper();

	@Mock
	private OrdenRepository ordenRepository;

	@InjectMocks
	private OrdenService ordenService;

	private Orden orden1;
	private Orden orden2;

	@BeforeAll
	static void setUpAll() throws IOException {
		mockWebServer = new MockWebServer();
		mockWebServer.start();
	}

	@AfterAll
	static void tearDownAll() throws IOException {
		mockWebServer.shutdown();
	}

	@BeforeEach
	void setUp() {
		orden1 = new Orden(1L, 10L, 2);
		orden2 = new Orden(2L, 20L, 5);

		ordenRepository = mock(OrdenRepository.class);

		WebClient webClient = WebClient.builder().baseUrl(mockWebServer.url("/").toString()).build();

		ordenService = new OrdenService(webClient, ordenRepository);
	}

	@Test
	void listarOrden_deberiaRetornarTodasLasOrdenes() {

		when(ordenRepository.findAll()).thenReturn(Flux.just(orden1, orden2));

		Flux<Orden> resultado = ordenService.listarOrden();

		StepVerifier.create(resultado).expectNext(orden1).expectNext(orden2).verifyComplete();

		verify(ordenRepository, times(1)).findAll();
	}

	@Test
	void listarOrdenPorID_deberiaRetornarOrdenCuandoExiste() {

		when(ordenRepository.findById(1L)).thenReturn(Mono.just(orden1));

		Mono<Orden> resultado = ordenService.listarOrdenPorID(1L);

		StepVerifier.create(resultado)
				.expectNextMatches(
						o -> o.getId().equals(1L) && o.getProductoId().equals(10L) && o.getCantidad().equals(2))
				.verifyComplete();

		verify(ordenRepository, times(1)).findById(1L);
	}

	@Test
	void listarOrdenPorID_deberiaRetornarVacioCuandoNoExiste() {

		when(ordenRepository.findById(99L)).thenReturn(Mono.empty());

		Mono<Orden> resultado = ordenService.listarOrdenPorID(99L);

		StepVerifier.create(resultado).verifyComplete();

		verify(ordenRepository, times(1)).findById(99L);
	}

	@Test
	void crearOrden() throws Exception {

		ProductoDto productoDto = new ProductoDto(1L, "Laptop", "equipo", 1500.0);

		mockWebServer.enqueue(new MockResponse().setBody(objectMapper.writeValueAsString(productoDto))
				.addHeader("Content-Type", "application/json"));

		Orden ordenGuardada = new Orden(10L, 1L, 2);
		when(ordenRepository.save(any(Orden.class))).thenReturn(Mono.just(ordenGuardada));
		Mono<OrderView> result = ordenService.crearOrden(new RequestDto(1L, 2));

		StepVerifier.create(result)
				.expectNextMatches(
						orderView -> orderView.nombreProducto().equals("Laptop") && orderView.total() == 3000.0)
				.verifyComplete();
	}
}
