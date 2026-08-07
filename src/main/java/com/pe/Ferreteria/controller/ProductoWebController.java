package com.pe.Ferreteria.controller;

import com.pe.Ferreteria.dto.ProductoDto;
import com.pe.Ferreteria.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
public class ProductoWebController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.traerProductos());
        model.addAttribute("nuevoProducto", new ProductoDto());
        return "productos";
    }

    @PostMapping("/guardar")
    public String guardarProducto(@Valid @ModelAttribute("nuevoProducto") ProductoDto dto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("productos", productoService.traerProductos());
            return "productos";
        }
        productoService.registrarProducto(dto);
        return "redirect:/productos";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarProducto(@PathVariable Integer id, @Valid @ModelAttribute ProductoDto dto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("productos", productoService.traerProductos());
            model.addAttribute("nuevoProducto", new ProductoDto());
            return "productos";
        }
        productoService.modificarProducto(id, dto);
        return "redirect:/productos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Integer id) {
        productoService.eliminarProducto(id);
        return "redirect:/productos";
    }
}
