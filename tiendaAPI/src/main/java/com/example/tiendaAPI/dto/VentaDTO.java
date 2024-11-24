package com.example.tiendaAPI.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VentaDTO {
    private Long codigoVenta;
    private Double totalVenta;
    private Double CantidadProducto;
    private String nombreCliente;
    private String apellidoCliente;

    public VentaDTO(){

    }

    public VentaDTO(Long codigoVenta, Double totalVenta, Double cantidadProducto, String nombreCliente, String apellidoCliente) {
        this.codigoVenta = codigoVenta;
        this.totalVenta = totalVenta;
        CantidadProducto = cantidadProducto;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
    }
}
