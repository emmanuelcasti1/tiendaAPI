package com.example.tiendaAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long codigo_producto;
    private String nombre;
    private String marca;
    private Double costo;
    private int cantidad_disponible;
    /*@ManyToMany(mappedBy = "listaProductos")
    @JsonIgnore  // Esto es para evitar la recursividad infinita al serializar a JSON
    private List<Venta> ventas;*/
    @OneToMany(mappedBy = "producto")
    @JsonIgnore
    private List<VentaProducto> ventas;

    public Producto(){

    }

    public Producto(Long codigo_producto, String nombre, String marca, Double costo, int cantidad_disponible) {
        this.codigo_producto = codigo_producto;
        this.nombre = nombre;
        this.marca = marca;
        this.costo = costo;
        this.cantidad_disponible = cantidad_disponible;
    }
}
