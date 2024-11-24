package com.example.tiendaAPI.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class VentaProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "codigo_venta")
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "codigo_producto")
    private Producto producto;

    private int cantidad;

    public VentaProducto() {
    }

    public VentaProducto(Venta venta, Producto producto, int cantidad) {
        this.venta = venta;
        this.producto = producto;
        this.cantidad = cantidad;
    }
}
