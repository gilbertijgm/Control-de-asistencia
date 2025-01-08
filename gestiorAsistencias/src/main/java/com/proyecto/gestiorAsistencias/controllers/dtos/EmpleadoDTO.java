package com.proyecto.gestiorAsistencias.controllers.dtos;

import com.proyecto.gestiorAsistencias.entities.Asistencia;
import com.proyecto.gestiorAsistencias.entities.HistorialPagos;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class EmpleadoDTO {

    private Long idEmpleado;
    @NotBlank(message = "Campo Nombre y Apellido es requerido, no puede estar vacio o nulo")
    private String nombreApellido;

    @NotBlank(message = "Campo DNI es requerido, no puede estar vacio o nulo")
    private String dni;

    @NotBlank(message = "Campo Cargo es requerido, no puede estar vacio o nulo")
    private String cargo;

    @NotNull(message = "Campo Horas laborales es requerido, no puede estar vacio o nulo")
    private int horasLaboralesDiarias;

    @NotNull(message = "Campo costo por hora es requerido, no puede estar vacio o nulo")
    private BigDecimal costoPorHora;

//    private Double horasTrabajadasPorMes;
//
//    private BigDecimal sueldoMensual;
//
//    private List<Asistencia> registroDeAsistencias = new ArrayList<>();
//
//    private List<HistorialPagos> registroDePagos = new ArrayList<>();
}
