package com.proyecto.gestiorAsistencias.controllers.dtos;


import com.proyecto.gestiorAsistencias.entities.Asistencia;
import com.proyecto.gestiorAsistencias.entities.Empleado;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AsistenciaDTO {

    private Long idAsistencia;

    private LocalDate fecha;

    private LocalTime hora;

    @NotBlank(message = "Campo obligatorio")
    @Enumerated(EnumType.STRING)
    private Asistencia.TipoRegistro tipoDeRegistro;


    private Empleado empleado;

    public enum TipoRegistro{
        Entrada, Salida
    }

}
