package com.test.cliente.persistence.repository;

import com.test.cliente.domain.model.Sales;
import com.test.cliente.domain.repository.ISalesRepository;
import com.test.cliente.persistence.CRUD.IVentasCRUDRepository;
import com.test.cliente.persistence.mapper.IVentasMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class VentasRepository implements ISalesRepository {

    @Autowired
    private IVentasMapper ventasMapper;

    @Autowired
    private IVentasCRUDRepository ventasCRUDRepository;

    @Override
    public Sales saveAndUpdate(Sales sales) {
        return ventasMapper.toSales(ventasCRUDRepository.save(ventasMapper.fromSales(sales)));
    }

    @Override
    public List<Sales> getSalesByDate(LocalDateTime dateStart, LocalDateTime dateEnd) {
        return ventasMapper.toSalesList(ventasCRUDRepository.findByFechaVentaBetween(dateStart, dateEnd));
    }
}
