package com.pe.Ferreteria.service;

import com.pe.Ferreteria.dto.ProductoDto;
import java.util.List;

public interface IProductoService {
    ProductoDto registrarProducto(ProductoDto productoDto);
    List<ProductoDto> traerProductos();
    ProductoDto buscarProducto(Integer id);
    ProductoDto modificarProducto(Integer id, ProductoDto productoDto);
    void eliminarProducto(Integer id);
}
