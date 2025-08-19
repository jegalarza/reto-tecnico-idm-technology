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

## Estructura del Proyecto
```
ms-reto-tecnico/
├── product-service/
│   ├── src/main/java/com/idm/producto/
│   │   ├── ProductoServiceApplication.java
│   │   ├── model/Producto.java
│   │   ├── repository/ProductoRepository.java
│   │   └── controller/ProductoController.java
│   └── src/main/resources/application.properties
│
├── order-service/
│   ├── src/main/java/com/idm/orden/
│   │   ├── OrdenServiceApplication.java
│   │   ├── model/Orden.java
│   │   ├── service/OrdenService.java
│   │   ├── repository/OrdenRepository.java
│   │   └── controller/OrdenController.java
│   └── src/main/resources/application.properties
│
├── api-gateway/
│   ├── src/main/java/com/idm/gateway/
│   │   ├── GatewayApplication.java
│   └── src/main/resources/application.properties
│
├── api-gateway/
│   ├── src/main/java/com/idm/discovery/
│   │   ├── DiscoveryApplication.java
│   └── src/main/resources/application.properties
│
└── pom.xml (padre)

```

---

## Clonar el repositorio
git clone https://github.com/jegalarza/reto-tecnico-idm-technology.git

## Compilar y ejecutar
- mvn clean package
- mvn spring-boot:run


