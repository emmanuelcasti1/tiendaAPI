package com.example.tiendaAPI.service;

import com.example.tiendaAPI.dto.DetalleFacturaDTO;
import com.example.tiendaAPI.model.Factura;
import com.example.tiendaAPI.model.Venta;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class FacturaService implements IGenerarFacturaService {

    @Autowired
    private IVentaService ventaService;

    @Override
    public Factura generarFactura(Long codigo_venta) {
        Venta venta = ventaService.findVenta(codigo_venta);
        if (venta == null) {
            throw new RuntimeException("Venta no encontrada con código: " + codigo_venta);
        }

        // Crear los detalles de la factura
        List<DetalleFacturaDTO> detalles = venta.getListaProductos().stream()
                .map(ventaProducto -> new DetalleFacturaDTO(ventaProducto.getProducto().getNombre(),
                        ventaProducto.getProducto().getMarca(),
                        ventaProducto.getProducto().getCosto(),
                        ventaProducto.getCantidad())).toList();

        // Crear la factura
        return new Factura(
                venta.getCodigo_venta(),
                venta.getUnCliente().getNombre() + " " + venta.getUnCliente().getApellido(),
                venta.getFecha_venta(),
                detalles,
                venta.getTotal());
    }

    @Override
    public void getFacturaPdf(Factura factura, String outputPath) {
        Document document = new Document();

        try {
            // Crear el escritor para el archivo PDF
            PdfWriter.getInstance(document, new FileOutputStream(outputPath));

            // Abrir el documento
            document.open();

            // Estilos
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            Font bodyFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

            // Agregar título
            Paragraph titulo = new Paragraph("Factura", titleFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setSpacingAfter(20);
            document.add(titulo);

            // Información general
            document.add(new Paragraph("Código de Venta: " + factura.getNumFactura(), bodyFont));
            document.add(new Paragraph("Cliente: " + factura.getCliente(), bodyFont));
            document.add(new Paragraph("Fecha de Venta: " + factura.getFechaVenta(), bodyFont));

            document.add(new Paragraph("\n")); // Espaciado

            // Crear tabla para los productos
            PdfPTable table = new PdfPTable(4); // 3 columnas
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);

            // Encabezados de la tabla
            PdfPCell cell;
            cell = new PdfPCell(new Paragraph("Producto", headerFont));
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph("Marca", headerFont));
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph("Costo", headerFont));
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph("Cantidad", headerFont));
            table.addCell(cell);

            // Agregar los datos de los productos
            for (DetalleFacturaDTO detalle : factura.getDetalles()) {
                table.addCell(new Paragraph(detalle.getProducto(), bodyFont));
                table.addCell(new Paragraph(detalle.getMarca(), bodyFont));
                table.addCell(new Paragraph(String.valueOf(detalle.getPrecioUnitario()), bodyFont));
                table.addCell(new Paragraph(String.valueOf(detalle.getCantidad()), bodyFont));
            }

            // Agregar la tabla al documento
            document.add(table);
            document.add(new Paragraph("\n")); // Espaciado
            document.add(new Paragraph("Total: $" + factura.getTotal(), headerFont));

        } catch (DocumentException | IOException e) {
            throw new RuntimeException("Error al generar la factura en PDF", e);
        } finally {
            // Cerrar el documento
            document.close();
        }
    }

    public void enviarFacturaPorCorreo(String destinatario, String asunto, String mensaje, String rutaArchivo) {
    }
}
