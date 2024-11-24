package com.example.tiendaAPI.model;

import com.example.tiendaAPI.dto.DetalleFacturaDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class Factura {
    private Long numFactura;
    private String cliente;
    private LocalDate fechaVenta;
    private List<DetalleFacturaDTO> detalles;
    private Double total;

    public Factura(){

    }

    public Factura(Long numFactura, String cliente, LocalDate fechaVenta, List<DetalleFacturaDTO> detalles, Double total) {
        this.numFactura = numFactura;
        this.cliente = cliente;
        this.fechaVenta = fechaVenta;
        this.detalles = detalles;
        this.total = total;
    }
}
