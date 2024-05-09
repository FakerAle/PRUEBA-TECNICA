package com.test.cliente.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medicament {

    private Long idMedication;
    private String nameMedication;
    private String factoryName;
    private LocalDateTime fabricationDate;
    private LocalDateTime expirationDate;
    private Long quantityStock;
    private BigInteger unitValue;
}
