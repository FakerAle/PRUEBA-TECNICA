package com.test.cliente.persistence.mapper;

import com.test.cliente.domain.model.Medicament;
import com.test.cliente.persistence.entity.Medicamento;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-07T23:42:06-0500",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class IMedicamentoMapperImpl implements IMedicamentoMapper {

    @Override
    public Medicament toMedicament(Medicamento medicamento) {
        if ( medicamento == null ) {
            return null;
        }

        Medicament medicament = new Medicament();

        medicament.setIdMedication( medicamento.getMedicamentoId() );
        medicament.setNameMedication( medicamento.getNombreMedicamento() );
        medicament.setFactoryName( medicamento.getNombreFabrica() );
        medicament.setFabricationDate( medicamento.getFechaFabricacion() );
        medicament.setExpirationDate( medicamento.getFechaVencimiento() );
        medicament.setQuantityStock( medicamento.getCantidadStock() );
        medicament.setUnitValue( medicamento.getValorUnitario() );

        return medicament;
    }

    @Override
    public List<Medicament> toMedicamentList(List<Medicamento> medicamentoList) {
        if ( medicamentoList == null ) {
            return null;
        }

        List<Medicament> list = new ArrayList<Medicament>( medicamentoList.size() );
        for ( Medicamento medicamento : medicamentoList ) {
            list.add( toMedicament( medicamento ) );
        }

        return list;
    }

    @Override
    public Medicamento fromMedicament(Medicament medicament) {
        if ( medicament == null ) {
            return null;
        }

        Medicamento medicamento = new Medicamento();

        medicamento.setMedicamentoId( medicament.getIdMedication() );
        medicamento.setNombreMedicamento( medicament.getNameMedication() );
        medicamento.setNombreFabrica( medicament.getFactoryName() );
        medicamento.setFechaFabricacion( medicament.getFabricationDate() );
        medicamento.setFechaVencimiento( medicament.getExpirationDate() );
        medicamento.setCantidadStock( medicament.getQuantityStock() );
        medicamento.setValorUnitario( medicament.getUnitValue() );

        return medicamento;
    }

    @Override
    public List<Medicamento> fromMedicamentList(List<Medicament> medicamentList) {
        if ( medicamentList == null ) {
            return null;
        }

        List<Medicamento> list = new ArrayList<Medicamento>( medicamentList.size() );
        for ( Medicament medicament : medicamentList ) {
            list.add( fromMedicament( medicament ) );
        }

        return list;
    }
}
