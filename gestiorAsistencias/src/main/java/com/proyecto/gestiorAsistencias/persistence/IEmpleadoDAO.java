package com.proyecto.gestiorAsistencias.persistence;

import com.proyecto.gestiorAsistencias.entities.Empleado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IEmpleadoDAO {

    Page<Empleado> findAllEmpleadoPage(Pageable pageable);

    List<Empleado> findAllEmpleado();

    //Optional me permite manejar los casos en que los valores sean nulos
    Optional<Empleado> findEmpleadoById(Long id);

    Empleado saveEmpleado(Empleado empleado);

    Empleado updateEmpleado(Empleado empleado);

    void deleteEmpleadoById(Long id);

    Optional<Empleado> findByDni(String dni);

}
