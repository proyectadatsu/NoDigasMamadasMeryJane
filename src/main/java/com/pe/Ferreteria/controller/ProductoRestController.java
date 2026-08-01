package com.pe.Ferreteria.controller;

import com.pe.Ferreteria.dto.ProductoDto;
import com.pe.Ferreteria.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ferreteria/productos")
public class ProductoRestController {

    @Autowired
    private ProductoService service;

    @GetMapping
    public ResponseEntity<?> traerProductor(){
        try {
            List<ProductoDto> productos = service.traerProductos();
            return ResponseEntity.ok(productos);
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.internalServerError().body("Error interno al listar los productos.");
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<?> registrarProducto(@RequestBody ProductoDto dto){
        try {
            ProductoDto productoCreado = service.registrarProducto(dto);
            return ResponseEntity.ok(productoCreado);
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.internalServerError().body("Error interno al registrar el producto.");
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> modificarProducto(@PathVariable Integer id, @RequestBody ProductoDto dto){
        try {
            ProductoDto productoActualizado = service.modificarProducto(id, dto);
            return ResponseEntity.ok(productoActualizado);
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.internalServerError().body("Error interno al modificar el producto.");
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Integer id){
        try {
            service.eliminarProducto(id);
            return ResponseEntity.ok("Producto eliminado exitosamente.");
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.internalServerError().body("Error interno al eliminar el producto.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarProducto(@PathVariable Integer id){
        try {
            ProductoDto productoEncontrado = service.buscarProducto(id);
            return ResponseEntity.ok(productoEncontrado);
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.internalServerError().body("Error interno al buscar el producto.");
        }
    }
}
