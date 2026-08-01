package com.pe.Ferreteria.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class ProductoDto {
    private Integer id_producto;
    private String nombre;
    private String marca;
    private String categoria;
    private Double precio;
    private Long stock;
    private String descripcion;
}
