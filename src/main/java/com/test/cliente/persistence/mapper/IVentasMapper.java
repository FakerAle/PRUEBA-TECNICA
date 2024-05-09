package com.test.cliente.persistence.mapper;

import com.test.cliente.domain.model.Medicament;
import com.test.cliente.domain.model.Sales;
import com.test.cliente.persistence.entity.Medicamento;
import com.test.cliente.persistence.entity.Ventas;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {IMedicamentoMapper.class})
public interface IVentasMapper {

    @Mappings({
            @Mapping(source = "codigo", target = "code"),
            @Mapping(source = "fechaVenta", target = "saleDate"),
            @Mapping(source = "cantidad", target = "quantity"),
            @Mapping(source = "valorUnitario", target = "unitValue"),
            @Mapping(source = "valorTotal", target = "totalValue"),
            @Mapping(source = "medicamentoId", target = "medicine"),
    })
    Sales toSales(Ventas ventas);

    List<Sales> toSalesList(List<Ventas> ventasList);

    @InheritInverseConfiguration
    Ventas fromSales(Sales sales);

    List<Ventas> fromSalesList(List<Sales> salesList);
}
