package com.example.tiendaAPI.controller;

import com.example.tiendaAPI.model.Factura;
import com.example.tiendaAPI.service.IGenerarFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;

@RestController
public class FacturaController {

    @Autowired
    private IGenerarFacturaService facturaService;

    @GetMapping("/factura/{codigo_venta}")
    public Factura getFactura(@PathVariable Long codigo_venta){
        return facturaService.generarFactura(codigo_venta);
    }

    @GetMapping("/factura/{codigo_venta}/descargar")
    public ResponseEntity<InputStreamResource> descargarFactura(@PathVariable Long codigo_venta) {
        try {
            // Generar la factura
            Factura factura = facturaService.generarFactura(codigo_venta);

            // Ruta temporal para el archivo PDF
            String outputPath = "factura_" + codigo_venta + ".pdf";

            // Generar el PDF
            facturaService.getFacturaPdf(factura, outputPath);

            // Preparar el archivo para la respuesta HTTP
            File file = new File(outputPath);
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

            // Configurar los encabezados de la respuesta
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=factura_" + codigo_venta + ".pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file.length())
                    .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                    .body(resource);

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(null); // Si la factura no se encuentra
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // Otros errores
        }
    }

}
