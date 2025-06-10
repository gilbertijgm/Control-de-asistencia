package com.proyecto.gestiorAsistencias.persistence.implementacion;

import com.proyecto.gestiorAsistencias.controllers.dtos.HistorialPagosDTO;
import com.proyecto.gestiorAsistencias.entities.HistorialPagos;
import com.proyecto.gestiorAsistencias.persistence.IEmpleadoDAO;
import com.proyecto.gestiorAsistencias.persistence.IHistorialPagoDAO;
import com.proyecto.gestiorAsistencias.repository.HistorialPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.List;

@Component
public class HistorialPagoDAOImpl implements IHistorialPagoDAO{



    @Autowired
    private HistorialPagoRepository historialPagoRepository;


    @Override
    public List<HistorialPagos> findAll() {
        List<HistorialPagos> historialPagos = historialPagoRepository.findAll();

        return historialPagos;
    }
}
