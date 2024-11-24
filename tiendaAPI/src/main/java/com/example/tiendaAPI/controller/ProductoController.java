package com.example.tiendaAPI.controller;

import com.example.tiendaAPI.model.Producto;
import com.example.tiendaAPI.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @PostMapping("/productos/crear")
    public String saveProducto(@RequestBody Producto producto){
        productoService.saveProducto(producto);
        return "Producto creado exitosamente";
    }

    @GetMapping("/productos")
    public List<Producto> getProductos(){
        return productoService.getPrductos();
    }

    @PutMapping("/productos/editar")
    public Producto editproducto(@RequestBody Producto producto){
        productoService.editProducto(producto);
        return productoService.findProducto(producto.getCodigo_producto());
    }

    @DeleteMapping("/productos/eliminar/{id_producto}")
    public String deleteProducto(@PathVariable Long id_producto){
        productoService.deleteProducto(id_producto);
        return "Producto eliminado";
    }

    @GetMapping("/productos/falta")
    public List<Producto> cantidadCinco(){
        return productoService.cantidadCinco();
    }


}
