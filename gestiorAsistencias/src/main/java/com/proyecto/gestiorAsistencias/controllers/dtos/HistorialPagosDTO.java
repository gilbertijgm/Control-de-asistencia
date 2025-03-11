package com.proyecto.gestiorAsistencias.controllers.dtos;



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
public class HistorialPagosDTO {

    private Long idHistorial;

    private LocalDate fechaMes;

    private double horasTrabajadas;

    private BigDecimal sueldoPagado;

    private LocalDateTime fechaDeGeneracion;

    private EmpleadoDTO empleadoDTO;
}
