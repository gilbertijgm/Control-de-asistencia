package com.proyecto.gestiorAsistencias.service;

import com.proyecto.gestiorAsistencias.controllers.dtos.HistorialPagosDTO;
import com.proyecto.gestiorAsistencias.entities.HistorialPagos;

public interface IHistorialPagoService {

    HistorialPagos save(HistorialPagosDTO historialPagosDTO);

}
