package com.turbotienda.TurboTienda;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller

public class Controlador {

    @Autowired
    private Repositorio repositorio;
    
    @GetMapping("/")
    public String index() {
        return "index";
    }
    
    @GetMapping("/productos")
    public String listarProductos(Model modelo) { 
        modelo.addAttribute("productos", repositorio.findAll());
        return "productos";
    }

    @GetMapping("/productos/nuevo")
    public String mostrarFormularioRegistro(Model modelo) {
        modelo.addAttribute("producto", new Modelo());
        return "formulario";
    }

    @PostMapping("/productos/guardar")
    public String guardarProducto(@ModelAttribute("producto") Modelo producto) {
        repositorio.save(producto);
        return "redirect:/productos";
    }

    @GetMapping("/productos/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Long id, Model modelo) {
        Optional<Modelo> productoOpt = repositorio.findById(id);
        if (productoOpt.isPresent()) {
            modelo.addAttribute("producto", productoOpt.get());
            return "formulario";
        }
        return "redirect:/productos";
    }

    @PostMapping("/productos/actualizar")
    public String actualizarProducto(@ModelAttribute("producto") Modelo producto) {
        repositorio.save(producto);
        return "redirect:/productos";
    }

    @GetMapping("/productos/eliminar/{id}")
    public String eliminarProducto(@PathVariable("id") Long id) {
        repositorio.deleteById(id);
        return "redirect:/productos";
    }
}