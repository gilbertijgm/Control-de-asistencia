package com.proyecto.gestiorAsistencias.controllers.dtos;


import com.proyecto.gestiorAsistencias.entities.Empleado;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalTime;

public class AsistenciaDTO {

    private Long idAsistencia;

    private LocalDate fecha;

    private LocalTime hora;

    @NotBlank(message = "Campo obligatorio")
    @Enumerated(EnumType.STRING)
    private TipoRegistro tipoDeRegistro;


    private Empleado empleado;

    public enum TipoRegistro{
        Entrada, Salida
    }
}
