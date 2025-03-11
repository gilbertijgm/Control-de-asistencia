package com.proyecto.gestiorAsistencias.persistence.implementacion;

import com.proyecto.gestiorAsistencias.entities.Empleado;
import com.proyecto.gestiorAsistencias.persistence.IEmpleadoDAO;
import com.proyecto.gestiorAsistencias.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class EmpleadoDAOImpl implements IEmpleadoDAO {

    @Autowired
    private EmpleadoRepository empleadoRepo;

    @Override
    public List<Empleado> findAllEmpleado() {
        List<Empleado> listaEmpleados = empleadoRepo.findAll();

        return listaEmpleados;
    }

    @Override
    public Optional<Empleado> findEmpleadoById(Long id) {
        return empleadoRepo.findById(id);
    }

    @Override
    public Empleado saveEmpleado(Empleado empleado) {
        return empleadoRepo.save(empleado);

    }

    @Override
    public Empleado updateEmpleado(Empleado empleado) {
        return empleadoRepo.save(empleado);

    }

    @Override
    public void deleteEmpleadoById(Long id) {
        empleadoRepo.deleteById(id);
    }

    @Override
    public Optional<Empleado> findByDni(String dni) {
        return empleadoRepo.findByDni(dni);
    }
}
