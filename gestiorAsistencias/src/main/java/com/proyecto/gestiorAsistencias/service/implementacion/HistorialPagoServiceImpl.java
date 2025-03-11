package com.proyecto.gestiorAsistencias.service.implementacion;

import com.proyecto.gestiorAsistencias.controllers.dtos.HistorialPagosDTO;
import com.proyecto.gestiorAsistencias.entities.HistorialPagos;
import com.proyecto.gestiorAsistencias.persistence.IHistorialPagoDAO;
import com.proyecto.gestiorAsistencias.service.IHistorialPagoService;
import org.springframework.beans.factory.annotation.Autowired;

public class HistorialPagoServiceImpl implements IHistorialPagoService {


    @Autowired
    private IHistorialPagoDAO historialPagoDAO;

    @Override
    public HistorialPagos save(HistorialPagosDTO historialPagosDTO) {

        return historialPagoDAO.save(historialPagosDTO);
    }
}
