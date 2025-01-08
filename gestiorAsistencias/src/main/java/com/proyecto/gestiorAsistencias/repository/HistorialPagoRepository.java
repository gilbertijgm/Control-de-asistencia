package com.proyecto.gestiorAsistencias.repository;

import com.proyecto.gestiorAsistencias.entities.HistorialPagos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialPagoRepository extends JpaRepository<HistorialPagos, Long> {
}
