package com.proyecto.gestiorAsistencias.persistence;

import com.proyecto.gestiorAsistencias.controllers.dtos.HistorialPagosDTO;
import com.proyecto.gestiorAsistencias.entities.HistorialPagos;

import java.util.List;

public interface IHistorialPagoDAO {

    //HistorialPagos save(HistorialPagosDTO historialPagos);

    List<HistorialPagos> findAll();
}
