package com.test.cliente.persistence.CRUD;

import com.test.cliente.persistence.entity.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface IVentasCRUDRepository extends JpaRepository<Ventas, Long> {

    @Query("SELECT v FROM Ventas v WHERE v.fechaVenta BETWEEN :startDate AND :endDate")
    List<Ventas> findByFechaVentaBetween(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

}
