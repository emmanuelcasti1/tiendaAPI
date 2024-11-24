package com.example.tiendaAPI.repository;

import com.example.tiendaAPI.model.VentaProducto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVentaProductoRepository extends JpaRepository<VentaProducto, Long> {
}
