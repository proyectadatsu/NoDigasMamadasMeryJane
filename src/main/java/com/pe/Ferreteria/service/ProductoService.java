package com.pe.Ferreteria.service;

import com.pe.Ferreteria.exception.NotFoundException;
import com.pe.Ferreteria.dto.ProductoDto;
import com.pe.Ferreteria.mapper.Mappers;
import com.pe.Ferreteria.model.Producto;
import com.pe.Ferreteria.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    private ProductoRepository repo;

    @Override
    public List<ProductoDto> traerProductos(){
        return repo.findAll().stream().map(Mappers::toDto).toList();
    }
    public ProductoDto registrarProducto(ProductoDto productoDto){
        Producto producto = Producto.builder()
                .id_producto(productoDto.getId_producto())
                .nombre(productoDto.getNombre())
                .marca(productoDto.getMarca())
                .categoria(productoDto.getCategoria())
                .precio(productoDto.getPrecio())
                .stock(productoDto.getStock())
                .descripcion(productoDto.getDescripcion())
                .build();
        return Mappers.toDto(repo.save(producto));
    }

    @Override
    public ProductoDto modificarProducto(Integer id, ProductoDto productoDto){
        Producto producto = repo.findById(id).orElseThrow(() -> new NotFoundException("Producto con ID " + id + " no encontrado."));

        producto.setId_producto(productoDto.getId_producto());
        producto.setNombre(productoDto.getNombre());
        producto.setMarca(productoDto.getMarca());
        producto.setCategoria(productoDto.getCategoria());
        producto.setPrecio(productoDto.getPrecio());
        producto.setStock(productoDto.getStock());
        producto.setDescripcion(productoDto.getDescripcion());
        return Mappers.toDto(repo.save(producto));
    }

    @Override
    public void eliminarProducto(Integer id){
        if(!repo.existsById(id)){
            throw new NotFoundException("Producto con ID " + id + " no encontrado.");
        }
        repo.deleteById(id);
    }

    @Override
    public ProductoDto buscarProducto(Integer id){
        Producto producto = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Producto con iD " + id + " no existe."));

        return Mappers.toDto(producto);
    }
}
