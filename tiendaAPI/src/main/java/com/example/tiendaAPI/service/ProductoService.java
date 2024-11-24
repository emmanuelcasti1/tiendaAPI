package com.example.tiendaAPI.service;

import com.example.tiendaAPI.model.Producto;
import com.example.tiendaAPI.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private IProductoRepository productoRepository;

    @Override
    public List<Producto> cantidadCinco(){
        List<Producto> listaProductos = new ArrayList<Producto>();
        for (Producto producto:productoRepository.findAll()) {
            if (producto.getCantidad_disponible()<5){
                listaProductos.add(producto);
            }
        }
        return listaProductos;
    }

    @Override
    public void saveProducto(Producto producto) { productoRepository.save(producto);
    }

    @Override
    public List<Producto> getPrductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto findProducto(Long id_producto) {
        return productoRepository.findById(id_producto).orElse(null);
    }

    @Override
    public void deleteProducto(Long id_producto) {
        productoRepository.deleteById(id_producto);
    }

    @Override
    public void editProducto(Producto producto) {
        this.saveProducto(producto);
    }
}
