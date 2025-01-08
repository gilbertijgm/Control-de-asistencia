package com.proyecto.gestiorAsistencias.repository;

import com.proyecto.gestiorAsistencias.entities.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado,Long> {

    Optional<Empleado> findByDni(String dni);
}
