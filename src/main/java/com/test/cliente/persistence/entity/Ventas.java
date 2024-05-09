package com.test.cliente.persistence.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "ventas", schema = "farmacia_esquema")
public class Ventas {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "fecha_venta")
    private LocalDateTime fechaVenta;

    @Column(name = "cantidad")
    private Long cantidad;

    @Column(name = "valor_unitario")
    private BigInteger valorUnitario;

    @Column(name = "valor_total")
    private BigInteger valorTotal;

    @ManyToOne
    @JoinColumn(name = "FK_MEDICAMENTO_ID", nullable = false)
    private Medicamento medicamentoId;
}
