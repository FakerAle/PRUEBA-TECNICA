package com.test.cliente.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDTOFilter {

    private String nameMedication;
    private String factoryName;
    private LocalDateTime fabricationDate;
}
