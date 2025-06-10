package com.proyecto.gestiorAsistencias.service;

import com.proyecto.gestiorAsistencias.controllers.dtos.HistorialPagosDTO;
import com.proyecto.gestiorAsistencias.entities.HistorialPagos;

import java.util.List;

public interface IHistorialPagoService {

    List<HistorialPagosDTO> findAll();

}
