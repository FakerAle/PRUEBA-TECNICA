package com.test.cliente.persistence.repository;

import com.test.cliente.domain.model.Medicament;
import com.test.cliente.domain.repository.IMedicamentRepository;
import com.test.cliente.persistence.CRUD.IMedicamentoCRUDRepository;
import com.test.cliente.persistence.mapper.IMedicamentoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class MedicamentoRepository implements IMedicamentRepository {

    @Autowired
    private IMedicamentoMapper medicamentoMapper;

    @Autowired
    private IMedicamentoCRUDRepository medicamentoCRUDRepository;

    @Override
    public List<Medicament> getMedicamentFilter(String factoryName,
                                                String nameMedication,
                                                LocalDateTime fabricationDate) {
        return medicamentoMapper.toMedicamentList(medicamentoCRUDRepository.
                findWithOptionalFilters(factoryName, nameMedication, fabricationDate));
    }

    @Override
    public Medicament findById(Long idMedicament) {
        return medicamentoMapper.toMedicament(medicamentoCRUDRepository.findById(idMedicament).orElse(null));
    }

    @Override
    public Medicament saveAndUpdate(Medicament medicament) {
        return medicamentoMapper.toMedicament(medicamentoCRUDRepository.save(medicamentoMapper.fromMedicament(medicament)));
    }

    @Override
    public void deleteMedicament(Long idMedicament) {
        medicamentoCRUDRepository.deleteById(idMedicament);
    }
}
