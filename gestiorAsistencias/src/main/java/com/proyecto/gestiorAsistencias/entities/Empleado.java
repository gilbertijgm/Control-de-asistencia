package com.proyecto.gestiorAsistencias.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Long idEmpleado;

    @Column(name = "nombre_apellido", length = 60)
    private String nombreApellido;

    @Column(length = 15)
    private String dni;

    @Column(length = 30)
    private String cargo;

    @Column(name = "horas_laborales_diarias")
    private int horasLaboralesDiarias;

    @Column(name = "costo_por_hora")
    private Double costoPorHora;

    @Column(name = "horas_trabajadas_mes")
    private Double horasTrabajadasPorMes =  0.00;

    @Column(name = "sueldo_mensual")
    private Double sueldoMensual =  0.00;

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @Column(name = "registro_asistencias")
    @JsonIgnore
    private List<Asistencia> registroDeAsistencias = new ArrayList<>();

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @Column(name = "registro_pagos")
    @JsonIgnore
    private List<HistorialPagos> registroDePagos = new ArrayList<>();
}

