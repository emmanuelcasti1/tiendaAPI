package com.example.tiendaAPI.repository;

import com.example.tiendaAPI.dto.VentaDTO;
import com.example.tiendaAPI.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IVentaRepository extends JpaRepository<Venta,Long> {

}
