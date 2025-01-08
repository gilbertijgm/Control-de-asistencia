package com.proyecto.gestiorAsistencias.repository;

import com.proyecto.gestiorAsistencias.entities.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
}
