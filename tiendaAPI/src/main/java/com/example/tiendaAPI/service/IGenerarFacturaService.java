package com.example.tiendaAPI.service;

import com.example.tiendaAPI.model.Factura;

public interface IGenerarFacturaService {
    public Factura generarFactura (Long codigo_venta);
    public void getFacturaPdf(Factura factura, String outputPath);
    public void sendFactura(String destinatario, String asunto, String mensaje, String rutaArchivo);
}
