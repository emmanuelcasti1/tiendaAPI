package com.example.tiendaAPI.service;

import com.example.tiendaAPI.dto.VentaDTO;
import com.example.tiendaAPI.model.Producto;
import com.example.tiendaAPI.model.Venta;

import java.time.LocalDate;
import java.util.List;

public interface IVentaService {

    public void saveVenta(Venta venta);
    public List<Venta> getVenta();
    public Venta findVenta(Long codigo_venta);
    public void deleteVenta(Long codigo_venta);
    public void editVenta(Venta venta);
    public List<Producto> getProductoVenta(Long codigo_venta);
    public String ventasPorDia(LocalDate fecha);
    public VentaDTO ventaMayor();
    public Producto findProductoById(Long idProducto);
}
