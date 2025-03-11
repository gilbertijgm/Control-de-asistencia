package com.proyecto.gestiorAsistencias.persistence.implementacion;

import com.proyecto.gestiorAsistencias.entities.Asistencia;
import com.proyecto.gestiorAsistencias.entities.Empleado;
import com.proyecto.gestiorAsistencias.persistence.IAsistenciaDAO;
import com.proyecto.gestiorAsistencias.repository.AsistenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class AsistenciaDAOImpl implements IAsistenciaDAO {

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    @Override
    public Asistencia registrarAsistencia(Asistencia asistencia) {
        return asistenciaRepository.save(asistencia);
    }

    @Override
    public List<Asistencia> registrosDelDia(Long idEmpleado, LocalDate fecha) {
        return asistenciaRepository.findByEmpleadoIdAndFecha(idEmpleado, fecha);
    }

    @Override
    public List<Asistencia> findAll() {
        return asistenciaRepository.findAll();
    }

    @Override
    public List<Asistencia> findByFecha(LocalDate fecha) {
        return asistenciaRepository.findByFecha(fecha);
    }


}
