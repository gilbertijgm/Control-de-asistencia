package com.proyecto.gestiorAsistencias.service;

import com.proyecto.gestiorAsistencias.controllers.dtos.AsistenciaDTO;
import com.proyecto.gestiorAsistencias.entities.Asistencia;

import java.time.LocalDate;
import java.util.List;

public interface IAsistenciaService {


    Asistencia registrarAsistencia(String dni);

    List<AsistenciaDTO> findAll();

    List<AsistenciaDTO> findByFecha(LocalDate fecha);
}
