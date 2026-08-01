package com.pe.Ferreteria.mapper;

import com.pe.Ferreteria.dto.ProductoDto;
import com.pe.Ferreteria.model.Producto;

public class Mappers {
    public static ProductoDto toDto(Producto producto){
        if (producto == null) return null;

        return ProductoDto.builder().id_producto(producto.getId_producto())
                .nombre(producto.getNombre())
                .marca(producto.getMarca())
                .categoria(producto.getCategoria())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .descripcion(producto.getDescripcion())
                .build();
    }
}
