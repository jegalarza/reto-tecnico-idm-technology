package com.idm.orden.dto;

public record OrderView (
		 Long id,
	     Long productoId,
	     Integer cantidad,
	     String nombreProducto,
	     Double precioUnitario,
	     Double total
){}
