package com.test.cliente.web.controller;

import com.test.cliente.domain.DTO.response.ResponseDTO;
import com.test.cliente.domain.model.Medicament;
import com.test.cliente.domain.request.RequestDTOFilter;
import com.test.cliente.domain.service.MedicamentService;
import com.test.cliente.domain.service.utils.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/medicament")
@Api(value = "Controlador que contiene los métodos del API relacionados los medicamentos")
@ApiResponses(value = {@ApiResponse(code = 200, message = "Respuesta exitosa."),
        @ApiResponse(code = 400, message = "Petición incorrecta."),
        @ApiResponse(code = 404, message = "No se encontraron datos."),
        @ApiResponse(code = 500, message = "Error del servidor.")})
public class MedicamentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MedicamentController.class);

    @Autowired
    MedicamentService medicamentService;

    @ApiOperation(value = "Crear medicamento", notes = "Permite crear el medicamento")
    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveMedicament(@Valid @RequestBody Medicament medicament) {

        ResponseDTO response;
        try {
            response = medicamentService.saveMedicament(medicament);

            if (response.getStatus() == HttpStatus.BAD_REQUEST.value()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            } else if (response.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            } else if (response.getStatus() == HttpStatus.OK.value()) {
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception ex) {
            response = Utils.errorResponse(LOGGER, ex);
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
        }
    }

    @ApiOperation(value = "Actualizar medicamento", notes = "Permite actualizar el medicamento")
    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateMedicament(
            @Valid @RequestBody Medicament medicament) {

        ResponseDTO response;
        try {
            response = medicamentService.saveMedicament(medicament);

            if (response.getStatus() == HttpStatus.BAD_REQUEST.value()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            } else if (response.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            } else if (response.getStatus() == HttpStatus.NOT_FOUND.value()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception ex) {
            response = Utils.errorResponse(LOGGER, ex);
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
        }
    }

    @ApiOperation(value = "Eliminar medicamento", notes = "Permite eliminar el medicamento")
    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteMedicament(@RequestParam(name = "idMedicament") Long idMedicament) {

        ResponseDTO response;
        try {
            response = medicamentService.deleteMedicament(idMedicament);

            if (response.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception ex) {
            response = Utils.errorResponse(LOGGER, ex);
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
        }
    }

    @ApiOperation(value = "obtener medicamento por id", notes = "Permite obtener el medicamento por id")
    @GetMapping(value = "/getId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getIdMedicament(@RequestParam(name = "idMedicament") Long idMedicament) {

        ResponseDTO response;
        try {
            response = medicamentService.getIdMedicament(idMedicament);

            if (response.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception ex) {
            response = Utils.errorResponse(LOGGER, ex);
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
        }
    }

    @ApiOperation(value = "obtener medicamento por filtros", notes = "Permite obtener el medicamento por filtros")
    @GetMapping(value = "/getMedicamentFilter", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getIdMedicamentFilter(@Valid @RequestBody RequestDTOFilter requestDTOFilter) {

        ResponseDTO response;
        try {
            response = medicamentService.getFilterMedicament(requestDTOFilter);

            if (response.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception ex) {
            response = Utils.errorResponse(LOGGER, ex);
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
        }
    }
}
