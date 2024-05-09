package com.test.cliente.persistence.mapper;

import com.test.cliente.domain.model.Medicament;
import com.test.cliente.persistence.entity.Medicamento;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMedicamentoMapper {

    @Mappings({
            @Mapping(source = "medicamentoId", target = "idMedication"),
            @Mapping(source = "nombreMedicamento", target = "nameMedication"),
            @Mapping(source = "nombreFabrica", target = "factoryName"),
            @Mapping(source = "fechaFabricacion", target = "fabricationDate"),
            @Mapping(source = "fechaVencimiento", target = "expirationDate"),
            @Mapping(source = "cantidadStock", target = "quantityStock"),
            @Mapping(source = "valorUnitario", target = "unitValue"),
    })
    Medicament toMedicament(Medicamento medicamento);

    List<Medicament> toMedicamentList(List<Medicamento> medicamentoList);

    @InheritInverseConfiguration
    Medicamento fromMedicament(Medicament medicament);

    List<Medicamento> fromMedicamentList(List<Medicament> medicamentList);
}
