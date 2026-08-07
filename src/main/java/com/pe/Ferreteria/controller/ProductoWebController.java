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

    // 1. List View
    @GetMapping
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.traerProductos());
        return "productos-list";
    }

    // 2. Dedicated Form View for Creating a New Product
    @GetMapping("/nuevo")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("producto", new ProductoDto());
        model.addAttribute("titulo", "Registrar Nuevo Producto");
        model.addAttribute("modoEdicion", false);
        return "producto-form";
    }

    // 3. Dedicated Form View for Editing an Existing Product
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        ProductoDto dto = productoService.buscarProducto(id);
        model.addAttribute("producto", dto);
        model.addAttribute("titulo", "Editar Producto #" + id);
        model.addAttribute("modoEdicion", true);
        return "producto-form";
    }

    // 4. Save/Update Handler
    @PostMapping("/guardar")
    public String guardarProducto(@Valid @ModelAttribute("producto") ProductoDto dto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            boolean esEdicion = dto.getId_producto() != null;
            model.addAttribute("titulo", esEdicion ? "Editar Producto #" + dto.getId_producto() : "Registrar Nuevo Producto");
            model.addAttribute("modoEdicion", esEdicion);
            return "producto-form";
        }

        if (dto.getId_producto() != null) {
            productoService.modificarProducto(dto.getId_producto(), dto);
        } else {
            productoService.registrarProducto(dto);
        }

        return "redirect:/productos";
    }

    // 5. Delete Handler
    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Integer id) {
        productoService.eliminarProducto(id);
        return "redirect:/productos";
    }
}
