package com.test.cliente.domain.service;

import com.test.cliente.domain.DTO.response.ResponseDTO;
import com.test.cliente.domain.model.Medicament;
import com.test.cliente.domain.repository.IMedicamentRepository;
import com.test.cliente.domain.request.RequestDTOFilter;
import com.test.cliente.domain.service.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicamentService {

    @Autowired
    private IMedicamentRepository medicamentRepository;

    public ResponseDTO saveMedicament(Medicament medicament) {
        ResponseDTO responseDTO;
        Medicament medicamentR;
        try {
            medicamentR = medicamentRepository.saveAndUpdate(medicament);

            if (medicamentR != null) {
                responseDTO = Utils.updateResponse(true, Optional.of(medicamentR), HttpStatus.CREATED.value(), 1, 1,
                        "Medicamento Creado.");
            } else {
                responseDTO = Utils.updateResponse(false, Optional.empty(), HttpStatus.BAD_REQUEST.value(), 1, 1,
                        "No se guardo el medicamento.");
            }
        } catch (Exception e) {
            responseDTO = Utils.updateResponse(false, Optional.empty(), HttpStatus.INTERNAL_SERVER_ERROR.value(), 1, 1,
                    e.getMessage());
        }
        return responseDTO;
    }

    public ResponseDTO deleteMedicament(Long idMedicament) {
        ResponseDTO responseDTO;
        try {
            medicamentRepository.deleteMedicament(idMedicament);
            responseDTO = Utils.updateResponse(true, Optional.empty(), HttpStatus.OK.value(), 1, 1,
                    "Medicamento eliminado.");
        } catch (Exception e) {
            responseDTO = Utils.updateResponse(false, Optional.empty(), HttpStatus.INTERNAL_SERVER_ERROR.value(), 1, 1,
                    e.getMessage());
        }
        return responseDTO;
    }

    public ResponseDTO getIdMedicament(Long idMedicament) {
        ResponseDTO responseDTO;
        Medicament medicamentR;

        try {
            medicamentR = medicamentRepository.findById(idMedicament);

            if (null != medicamentR) {
                responseDTO = Utils.updateResponse(true, Optional.of(medicamentR), HttpStatus.OK.value(), 1,
                        1, "Consulta Exitosa.");
            } else {
                responseDTO = Utils.updateResponse(false, Optional.empty(), HttpStatus.NOT_FOUND.value(), 1, 1,
                        "No se encontraron registros.");
            }
        } catch (Exception e) {
            responseDTO = Utils.updateResponse(false, Optional.empty(), HttpStatus.INTERNAL_SERVER_ERROR.value(), 1, 1,
                    e.getMessage());
        }
        return responseDTO;
    }

    public ResponseDTO getFilterMedicament(RequestDTOFilter requestDTOFilter) {
        ResponseDTO responseDTO;
        List<Medicament> medicamentR;

        try {
            medicamentR = medicamentRepository.getMedicamentFilter(requestDTOFilter.getFactoryName(), requestDTOFilter.getNameMedication(), requestDTOFilter.getFabricationDate());

            if (null != medicamentR) {
                responseDTO = Utils.updateResponse(true, Optional.of(medicamentR), HttpStatus.OK.value(), 1,
                        1, "Consulta Exitosa.");
            } else {
                responseDTO = Utils.updateResponse(false, Optional.empty(), HttpStatus.NOT_FOUND.value(), 1, 1,
                        "No se encontraron registros.");
            }
        } catch (Exception e) {
            responseDTO = Utils.updateResponse(false, Optional.empty(), HttpStatus.INTERNAL_SERVER_ERROR.value(), 1, 1,
                    e.getMessage());
        }
        return responseDTO;
    }
}
