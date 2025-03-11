package com.proyecto.gestiorAsistencias.persistence;

import com.proyecto.gestiorAsistencias.entities.Asistencia;

import java.time.LocalDate;
import java.util.List;

public interface IAsistenciaDAO {

    Asistencia registrarAsistencia(Asistencia asistencia);

    List<Asistencia> registrosDelDia(Long idEmpleado, LocalDate fecha);

    List<Asistencia> findAll();

    List<Asistencia> findByFecha(LocalDate fecha);
}
