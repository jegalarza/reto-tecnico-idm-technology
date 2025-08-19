package com.example.product.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table("productos")
public class Producto {
    @Id
    private Long id;
    private String nombre;
    private String categoria;
    private Double precio;

   
}
