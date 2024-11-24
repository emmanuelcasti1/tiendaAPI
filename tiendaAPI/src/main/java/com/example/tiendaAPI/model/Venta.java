package com.example.tiendaAPI.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long codigo_venta;
    private LocalDate fecha_venta;
    private Double total;
    /*@ManyToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "venta_producto",  // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "codigo_venta"),  // Columna que se refiere a Venta
            inverseJoinColumns = @JoinColumn(name = "codigo_producto")  // Columna que se refiere a Producto
    )
    private List<Producto> listaProductos;*/
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VentaProducto> listaProductos;

    @OneToOne
            @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    private Cliente unCliente;

    public Venta(){

    }

    public Venta(Long codigo_venta, LocalDate fecha_venta, Cliente unCliente) {
        this.codigo_venta = codigo_venta;
        this.fecha_venta = fecha_venta;
        this.unCliente = unCliente;
    }
}
