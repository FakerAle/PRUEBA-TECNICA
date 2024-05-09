package com.test.cliente.persistence.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "medicamento", schema = "farmacia_esquema")
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_medicamento")
    private Long medicamentoId;

    @Size(max = 20, message = "Excede la longitud permitida.")
    @Column(name = "nombre_medicamento")
    private String nombreMedicamento;

    @Size(max = 20, message = "Excede la longitud permitida.")
    @Column(name = "nombre_fabrica")
    private String nombreFabrica;

    @Column(name = "fecha_fabricacion")
    private LocalDateTime fechaFabricacion;

    @Column(name = "fecha_vencimiento")
    private LocalDateTime fechaVencimiento;

    @Column(name = "cantidad_producto")
    private Long cantidadStock;

    @Column(name = "valor_unitario")
    private BigInteger valorUnitario;

    @OneToMany(mappedBy = "medicamentoId", fetch = FetchType.LAZY)
    private List<Ventas> ventasList;
}
