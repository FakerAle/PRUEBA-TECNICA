package com.test.cliente.domain.repository;

import com.test.cliente.domain.model.Sales;

import java.time.LocalDateTime;
import java.util.List;

public interface ISalesRepository {

    Sales saveAndUpdate(Sales medicament);

    List<Sales> getSalesByDate(LocalDateTime dateStart, LocalDateTime dateEnd);
}
