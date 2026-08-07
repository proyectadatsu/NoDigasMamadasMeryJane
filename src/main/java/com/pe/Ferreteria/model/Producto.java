package com.pe.Ferreteria.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_producto;

    @NotBlank(message = "El nombre del producto no puede estar vacío.")
    private String nombre;

    @NotBlank(message = "La marca no puede estar vacía.")
    private String marca;

    @NotBlank(message = "La categoría no puede estar vacía.")
    private String categoria;

    @NotNull(message = "El precio es obligatorio.")
    @PositiveOrZero(message = "El precio no puede ser negativo.")
    @Max(value = 999999, message = "El precio no puede exceder 999,999.")
    private Double precio;

    @NotNull(message = "El stock es obligatorio.")
    @Min(value = 0, message = "El stock no puede ser negativo.")
    @Max(value = 10000, message = "El stock máximo permitido es 10,000 unidades.")
    private Long stock;

    private String descripcion;
}
