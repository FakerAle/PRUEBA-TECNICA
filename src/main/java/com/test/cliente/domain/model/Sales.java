package com.test.cliente.domain.model;

import com.test.cliente.persistence.entity.Medicamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sales {

    private Long code;
    private LocalDateTime saleDate;
    private Long quantity;
    private BigInteger unitValue;
    private BigInteger totalValue;
    private Medicament medicine;
}
