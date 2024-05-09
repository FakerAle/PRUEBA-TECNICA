package com.test.cliente.persistence.mapper;

import com.test.cliente.domain.model.Sales;
import com.test.cliente.persistence.entity.Ventas;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-07T23:59:00-0500",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class IVentasMapperImpl implements IVentasMapper {

    @Autowired
    private IMedicamentoMapper iMedicamentoMapper;

    @Override
    public Sales toSales(Ventas ventas) {
        if ( ventas == null ) {
            return null;
        }

        Sales sales = new Sales();

        sales.setCode( ventas.getCodigo() );
        sales.setSaleDate( ventas.getFechaVenta() );
        sales.setQuantity( ventas.getCantidad() );
        sales.setUnitValue( ventas.getValorUnitario() );
        sales.setTotalValue( ventas.getValorTotal() );
        sales.setMedicine( iMedicamentoMapper.toMedicament( ventas.getMedicamentoId() ) );

        return sales;
    }

    @Override
    public List<Sales> toSalesList(List<Ventas> ventasList) {
        if ( ventasList == null ) {
            return null;
        }

        List<Sales> list = new ArrayList<Sales>( ventasList.size() );
        for ( Ventas ventas : ventasList ) {
            list.add( toSales( ventas ) );
        }

        return list;
    }

    @Override
    public Ventas fromSales(Sales sales) {
        if ( sales == null ) {
            return null;
        }

        Ventas ventas = new Ventas();

        ventas.setCodigo( sales.getCode() );
        ventas.setFechaVenta( sales.getSaleDate() );
        ventas.setCantidad( sales.getQuantity() );
        ventas.setValorUnitario( sales.getUnitValue() );
        ventas.setValorTotal( sales.getTotalValue() );
        ventas.setMedicamentoId( iMedicamentoMapper.fromMedicament( sales.getMedicine() ) );

        return ventas;
    }

    @Override
    public List<Ventas> fromSalesList(List<Sales> salesList) {
        if ( salesList == null ) {
            return null;
        }

        List<Ventas> list = new ArrayList<Ventas>( salesList.size() );
        for ( Sales sales : salesList ) {
            list.add( fromSales( sales ) );
        }

        return list;
    }
}
