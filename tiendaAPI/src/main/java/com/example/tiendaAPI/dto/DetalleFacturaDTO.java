package com.example.tiendaAPI.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetalleFacturaDTO {
    private String producto;
    private String marca;
    private Double precioUnitario;
    private int cantidad;

    public DetalleFacturaDTO() {
    }

    public DetalleFacturaDTO(String producto, String marca, Double precioUnitario, int cantidad) {
        this.producto = producto;
        this.marca = marca;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
    }
}
