package com.proyecto.gestiorAsistencias.persistence.implementacion;

import com.proyecto.gestiorAsistencias.controllers.dtos.HistorialPagosDTO;
import com.proyecto.gestiorAsistencias.entities.HistorialPagos;
import com.proyecto.gestiorAsistencias.persistence.IEmpleadoDAO;
import com.proyecto.gestiorAsistencias.persistence.IHistorialPagoDAO;
import com.proyecto.gestiorAsistencias.repository.HistorialPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class HistorialPagoDAOImpl implements IHistorialPagoDAO{

    @Autowired
    private IEmpleadoDAO iEmpleadoDAO;

    @Autowired
    private IHistorialPagoDAO historialDAO;

    @Autowired
    private HistorialPagoRepository historialPagoRepository;


    @Override
    public HistorialPagos save(HistorialPagosDTO historialPagos) {
        return historialDAO.save(historialPagos);
    }
}
