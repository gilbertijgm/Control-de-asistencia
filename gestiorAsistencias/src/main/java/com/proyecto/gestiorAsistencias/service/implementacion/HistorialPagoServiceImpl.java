package com.proyecto.gestiorAsistencias.service.implementacion;

import com.proyecto.gestiorAsistencias.controllers.dtos.HistorialPagosDTO;
import com.proyecto.gestiorAsistencias.entities.HistorialPagos;
import com.proyecto.gestiorAsistencias.persistence.IHistorialPagoDAO;
import com.proyecto.gestiorAsistencias.service.IHistorialPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistorialPagoServiceImpl implements IHistorialPagoService {


    @Autowired
    private IHistorialPagoDAO historialPagoDAO;


    @Override
    public List<HistorialPagosDTO> findAll() {
        List<HistorialPagosDTO> historialDTO = historialPagoDAO.findAll().stream()
                .map(historial -> HistorialPagosDTO.builder()
                        .idHistorial(historial.getIdHistorial())
                        .fechaMes(historial.getFechaMes())
                        .horasTrabajadas(historial.getHorasTrabajadas())
                        .sueldoPagado(historial.getSueldoPagado())
                        .fechaDeGeneracion(historial.getFechaDeGeneracion())
                        .empleado(historial.getEmpleado())
                        .build())
                .toList();

        return historialDTO;
    }
}
