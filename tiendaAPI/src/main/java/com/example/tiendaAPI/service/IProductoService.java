package com.example.tiendaAPI.service;

import com.example.tiendaAPI.model.Producto;

import java.util.List;

public interface IProductoService {
    public void saveProducto(Producto producto);
    public List<Producto> getPrductos();
    public Producto findProducto(Long id_producto);
    public void deleteProducto(Long id_producto);
    public void editProducto(Producto producto);
    public List<Producto> cantidadCinco();
}
