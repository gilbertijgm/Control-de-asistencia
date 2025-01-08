package com.proyecto.gestiorAsistencias.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HistorialPagos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial")
    private Long idHistorial;

    @Column(name = "fecha_mes")
    private LocalDate fechaMes;

    @Column(name = "horas_trabajadas")
    private double horasTrabajadas;

    @Column(name = "sueldo_pagado")
    private BigDecimal sueldoPagado;

    @Column(name = "fecha_generacion")
    private LocalDateTime fechaDeGeneracion;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;
}
