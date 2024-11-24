package com.example.tiendaAPI.service;

import com.example.tiendaAPI.dto.VentaDTO;
import com.example.tiendaAPI.model.Producto;
import com.example.tiendaAPI.model.Venta;
import com.example.tiendaAPI.model.VentaProducto;
import com.example.tiendaAPI.repository.IProductoRepository;
import com.example.tiendaAPI.repository.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VentaService implements IVentaService{

    @Autowired
    private IVentaRepository ventaRepository;

    @Autowired
    private IProductoRepository productoRepository;


    @Override
    public void saveVenta(Venta venta) {
        double total = 0.0;

        for (VentaProducto ventaProducto:venta.getListaProductos()) {
            Producto producto = productoRepository.findById(ventaProducto.getProducto().getCodigo_producto())
                    .orElse(null);
            if (producto != null){
                int nuevaCantidad = producto.getCantidad_disponible() - ventaProducto.getCantidad();
                producto.setCantidad_disponible(nuevaCantidad);
                productoRepository.save(producto);
                total += ventaProducto.getCantidad()* producto.getCosto();
                ventaProducto.setVenta(venta);
            }
            venta.setTotal(total);
            ventaRepository.save(venta);
        }
    }


    @Override
    public List<Venta> getVenta() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta findVenta(Long codigo_venta) {
        return ventaRepository.findById(codigo_venta).orElse(null);
    }

    @Override
    public void deleteVenta(Long codigo_venta) {
        ventaRepository.deleteById(codigo_venta);
    }

    @Override
    public void editVenta(Venta venta) {
        this.saveVenta(venta);
    }

    @Override
    public List<Producto> getProductoVenta(Long codigo_venta) {
        Venta venta = this.findVenta(codigo_venta);
        return venta != null ? venta.getListaProductos().stream().
                map(VentaProducto::getProducto).toList() : null;
        /*List<Producto> productos = new ArrayList<Producto>();
        Venta venta = this.findVenta(codigo_venta);
        productos.add((Producto) venta.getListaProductos());
        return productos;*/
    }


    @Override
    public String ventasPorDia(LocalDate fecha) {
        Double totalVentas = 0.0;
        int ventasDia = 0;


        for (Venta venta: this.getVenta()) {
            if (venta.getFecha_venta().equals(fecha)){
                totalVentas += venta.getTotal();
                ventasDia++;
            }
        }
        

        return "Total ventas del día " + fecha + ": " + totalVentas + " con " + ventasDia +
                " ventas.";

    }

    @Override
    public VentaDTO ventaMayor() {
        VentaDTO ventaDTO = new VentaDTO();
        Double ventaMayor = 0.0;
        List<Venta> ventas = this.getVenta();
        for (Venta venta: ventas) {
            if (venta.getTotal() > ventaMayor){
                ventaDTO.setCodigoVenta(venta.getCodigo_venta());
                ventaDTO.setTotalVenta(venta.getTotal());
                ventaDTO.setCantidadProducto((double) venta.getListaProductos().size());
                ventaDTO.setNombreCliente(venta.getUnCliente().getNombre());
                ventaDTO.setApellidoCliente(venta.getUnCliente().getApellido());
            }
        }
        return ventaDTO;
    }

    @Override
    public Producto findProductoById(Long idProducto) {
        return productoRepository.findById(idProducto).orElse(null);
    }


}


