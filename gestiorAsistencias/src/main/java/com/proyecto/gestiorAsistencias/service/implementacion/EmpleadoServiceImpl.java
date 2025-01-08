package com.proyecto.gestiorAsistencias.service.implementacion;

import com.proyecto.gestiorAsistencias.entities.Empleado;
import com.proyecto.gestiorAsistencias.persistence.IEmpleadoDAO;
import com.proyecto.gestiorAsistencias.service.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

    @Autowired
    private IEmpleadoDAO empleadoDAO;

    @Override
    public List<Empleado> findAllEmpleado() {
        List<Empleado> listaEmpleados = empleadoDAO.findAllEmpleado();

        return  listaEmpleados;
    }

    @Override
    public Optional<Empleado> findEmpleadoById(Long id) {
        return empleadoDAO.findEmpleadoById(id);
    }

    @Override
    public Empleado saveEmpleado(Empleado empleado) {
        empleadoDAO.saveEmpleado(empleado);
        return empleado;
    }

    @Override
    public void deleteEmpleadoById(Long id) {
        empleadoDAO.deleteEmpleadoById(id);
    }

    @Override
    public Optional<Empleado> findByDni(String dni) {
        return empleadoDAO.findByDni(dni);
    }
}
