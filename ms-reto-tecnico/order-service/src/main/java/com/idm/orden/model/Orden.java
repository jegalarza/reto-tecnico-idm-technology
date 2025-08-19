package com.idm.orden.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table("ordenes")
public class Orden {
	
    @Id
    private Long id;
    
    @Column("producto_id")
    private Long productoId;
    private Integer cantidad;

}
