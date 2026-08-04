package com.pe.Ferreteria.controller;

import com.pe.Ferreteria.dto.ProductoDto;
import com.pe.Ferreteria.service.IProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
public class ProductoWebController {

    private final IProductoService service;

    public ProductoWebController(IProductoService service){
        this.service = service;
    }

    @GetMapping
    public String traerProductos(Model model){
        model.addAttribute(
                "productos",
                service.traerProductos()
        );

        return "productos/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model){

        model.addAttribute("producto", new ProductoDto());
        model.addAttribute("titulo", "Registrar Producto");

        return "productos/formulario";
    }

    @PostMapping("/crear")
    public String crearProducto(@ModelAttribute ProductoDto dto, Model model){

        ProductoDto resultado;
        if (dto.getId_producto() == null){
            resultado = service.registrarProducto(dto);
        } else {
            resultado = service.modificarProducto(
                    dto.getId_producto(),
                    dto
            );
        }

        if (resultado == null) {
            model.addAttribute("producto", dto);
            model.addAttribute(
                    "titulo",
                    dto.getId_producto() == null
                            ? "Registrar producto"
                            : "Editar producto"
            );
            model.addAttribute(
                    "error",
                    "Revisa los datos. Nombre, marca y categoria son obligatorios. " +
                            "El precio debe ser mayor a cero y el stock no puede ser negativo."
            );

            return "productos/formulario";
        }

        return "redirect:/productos";

    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(
            @PathVariable Integer id,
            Model model) {

        ProductoDto dto = service.buscarProducto(id);

        if (dto == null){
            return "redirect:/productos";
        }

        model.addAttribute("producto", dto);
        model.addAttribute("titulo", "Editar producto");

        return "productos/formulario";
    }

    @PostMapping("/eliminar{id}")
    public String eliminarProducto(@PathVariable Integer id){

        service.eliminarProducto(id);

        return "redirect:/productos";
    }
}
