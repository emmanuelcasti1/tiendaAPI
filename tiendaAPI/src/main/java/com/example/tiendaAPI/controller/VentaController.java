package com.example.tiendaAPI.controller;

import com.example.tiendaAPI.dto.VentaDTO;
import com.example.tiendaAPI.model.Producto;
import com.example.tiendaAPI.model.Venta;
import com.example.tiendaAPI.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class VentaController {

    @Autowired
    private IVentaService ventaService;

    @PostMapping("/ventas/crear")
    public String saveVenta(@RequestBody Venta venta){
        ventaService.saveVenta(venta);
        return "Venta creada exitosamente";
    }

    @GetMapping("/ventas")
    public List<Venta> getVentas(){
        return ventaService.getVenta();
    }

    @PutMapping("/ventas/editar")
    public Venta editVenta(@RequestBody Venta venta){
        ventaService.editVenta(venta);
        return ventaService.findVenta(venta.getCodigo_venta());
    }

    @DeleteMapping("/ventas/eliminar/{id_venta}")
    public String deleteVenta(@PathVariable Long id_venta){
        ventaService.deleteVenta(id_venta);
        return "Venta eliminada";
    }

    @GetMapping("/ventas/productos/{codigo_venta}")
    public List<Producto> getProductoVenta(@PathVariable Long codigo_venta){
        return ventaService.getProductoVenta(codigo_venta);
    }

    @GetMapping("/ventas/{fecha_venta}")
    public String ventasPorDia(@PathVariable LocalDate fecha_venta){
        return ventaService.ventasPorDia(fecha_venta);
    }

    @GetMapping("/ventas/mayor")
    public VentaDTO ventaMayor(){
        return ventaService.ventaMayor();
    }
}
