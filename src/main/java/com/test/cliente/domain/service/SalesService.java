package com.test.cliente.domain.service;

import com.test.cliente.domain.DTO.response.ResponseDTO;
import com.test.cliente.domain.model.Medicament;
import com.test.cliente.domain.model.Sales;
import com.test.cliente.domain.repository.IMedicamentRepository;
import com.test.cliente.domain.repository.ISalesRepository;
import com.test.cliente.domain.request.RequestDTOFilter;
import com.test.cliente.domain.service.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SalesService {

    @Autowired
    private ISalesRepository salesRepository;

    @Autowired
    private IMedicamentRepository medicamentRepository;

    public ResponseDTO saveSales(Sales sales) {
        ResponseDTO responseDTO;
        Sales salesR;
        Medicament medicament;
        try {

            medicament = medicamentRepository.findById(sales.getMedicine().getIdMedication());

            if (null == medicament) {
                return Utils.updateResponse(false, Optional.empty(), HttpStatus.BAD_REQUEST.value(), 1, 1,
                        "El medicamento que intentas vender no existe.");
            }

            if (sales.getQuantity() > medicament.getQuantityStock()) {
                return Utils.updateResponse(false, Optional.empty(), HttpStatus.BAD_REQUEST.value(), 1, 1,
                        "El medicamento que intentas vender esta fuera stock.");
            }

            Long calQuantityStock = medicament.getQuantityStock() - sales.getQuantity();
            medicament.setQuantityStock(calQuantityStock);
            medicamentRepository.saveAndUpdate(medicament);

            sales.setUnitValue(medicament.getUnitValue());
            BigInteger calPriceTotal = medicament.getUnitValue().multiply(BigInteger.valueOf(sales.getQuantity()));
            sales.setTotalValue(calPriceTotal);
            salesR = salesRepository.saveAndUpdate(sales);

            if (salesR != null) {
                responseDTO = Utils.updateResponse(true, Optional.of(salesR), HttpStatus.CREATED.value(), 1, 1,
                        "venta Creado.");
            } else {
                responseDTO = Utils.updateResponse(false, Optional.empty(), HttpStatus.BAD_REQUEST.value(), 1, 1,
                        "No se guardo la venta.");
            }
        } catch (Exception e) {
            responseDTO = Utils.updateResponse(false, Optional.empty(), HttpStatus.INTERNAL_SERVER_ERROR.value(), 1, 1,
                    e.getMessage());
        }
        return responseDTO;
    }

    public ResponseDTO getSalesByDate(LocalDateTime dateStart, LocalDateTime dateEnd) {
        ResponseDTO responseDTO;
        List<Sales> medicamentR;

        try {
            medicamentR = salesRepository.getSalesByDate(dateStart, dateEnd);

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
