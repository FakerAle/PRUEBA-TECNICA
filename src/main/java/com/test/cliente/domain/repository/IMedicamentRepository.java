package com.test.cliente.domain.repository;

import com.test.cliente.domain.model.Medicament;

import java.time.LocalDateTime;
import java.util.List;

public interface IMedicamentRepository {

    List<Medicament> getMedicamentFilter(String factoryName,
                                         String nameMedication,
                                         LocalDateTime fabricationDate);

    Medicament findById(Long idMedicament);

    Medicament saveAndUpdate(Medicament medicament);

    void deleteMedicament(Long idMedicament);
}
