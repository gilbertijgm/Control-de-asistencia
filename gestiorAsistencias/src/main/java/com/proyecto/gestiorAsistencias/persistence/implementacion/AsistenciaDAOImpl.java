package com.proyecto.gestiorAsistencias.persistence.implementacion;

import com.proyecto.gestiorAsistencias.entities.Asistencia;
import com.proyecto.gestiorAsistencias.entities.Empleado;
import com.proyecto.gestiorAsistencias.persistence.IAsistenciaDAO;
import com.proyecto.gestiorAsistencias.repository.AsistenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AsistenciaDAOImpl implements IAsistenciaDAO {

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    @Override
    public void registrarAsistencia() {
        //asistenciaRepository.save(asistencia);
    }
}
