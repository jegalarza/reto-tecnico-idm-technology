# Reto Técnico IDM Technology - Microservicios comunicacion entre si con WebClient

Este proyecto es una MS reactiva construida con **Spring Boot WebFlux** , **Spring Data R2DBC** y **WebClient** para generar ordenes segun los productos existentes.  
Incluye persistencia en **H2 en memoria**, pruebas unitarias.

---

## Características
- API 100% reactiva con **WebFlux** y **WebClient**
- Persistencia usando **R2DBC** (H2 en memoria)
- El listado de productos y ordenes
- Manejo de errores y validaciones
- Tests para pruebas unitarias con JUnit

---

## Tecnologías
- **Java 17**
- **Spring Boot 3.5.4**
- **Spring WebFlux**
- - **Spring WebClient**
- **Spring Data R2DBC**
- **H2 Database** (modo memoria)
- **Lombok**
- **JUnit 5** + **Reactor Test**

---

## Clonar el repositorio
git clone https://github.com/jegalarza/reto-tecnico-idm-technology.git

## Compilar y ejecutar
- mvn clean package
- mvn spring-boot:run


