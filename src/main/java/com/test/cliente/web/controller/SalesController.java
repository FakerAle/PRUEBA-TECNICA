package com.test.cliente.web.controller;

import com.test.cliente.domain.DTO.response.ResponseDTO;
import com.test.cliente.domain.model.Sales;
import com.test.cliente.domain.service.SalesService;
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
import java.time.LocalDateTime;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/sales")
@Api(value = "Controlador que contiene los métodos del API relacionados los ventas")
@ApiResponses(value = {@ApiResponse(code = 200, message = "Respuesta exitosa."),
        @ApiResponse(code = 400, message = "Petición incorrecta."),
        @ApiResponse(code = 404, message = "No se encontraron datos."),
        @ApiResponse(code = 500, message = "Error del servidor.")})
public class SalesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MedicamentController.class);

    @Autowired
    SalesService salesService;

    @ApiOperation(value = "Crear venta", notes = "Permite crear la venta")
    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveSales(@Valid @RequestBody Sales sales) {

        ResponseDTO response;
        try {
            response = salesService.saveSales(sales);

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

    @ApiOperation(value = "obtener ventas por fechas", notes = "Permite obtener las ventas por fechas")
    @GetMapping(value = "/getSalesDate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getSalesFilterDate(@RequestParam(name = "dateStart") String dateStart, @RequestParam(name = "dateEnd") String dateEnd) {

        ResponseDTO response;
        try {
            response = salesService.getSalesByDate(LocalDateTime.parse(dateStart), LocalDateTime.parse(dateEnd));

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
