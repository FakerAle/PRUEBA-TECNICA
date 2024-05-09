package com.test.cliente.persistence.CRUD;

import com.test.cliente.persistence.entity.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface IMedicamentoCRUDRepository extends JpaRepository<Medicamento, Long> {

    @Query("SELECT m FROM Medicamento m "
            + "WHERE m.medicamentoId > 0 "
            + "AND(m.nombreFabrica = :factoryName OR '0' = :factoryName)"
            + "AND(m.nombreMedicamento = :nameMedication OR '0' = :nameMedication)"
            + "AND(m.fechaFabricacion = :fabricationDate OR '0' = :fabricationDate)")
    List<Medicamento> findWithOptionalFilters(
            @Param("factoryName") String factoryName,
            @Param("nameMedication") String nameMedication,
            @Param("fabricationDate") LocalDateTime fabricationDate
    );
}
