package com.proyecto.gestiorAsistencias.controllers.dtos;



import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class HistorialPagosDTO {

    private Long idHistorial;

    private LocalDate fechaMes;

    private double horasTrabajadas;

    private BigDecimal sueldoPagado;

    private LocalDateTime fechaDeGeneracion;

    private EmpleadoDTO empleadoDTO;
}
