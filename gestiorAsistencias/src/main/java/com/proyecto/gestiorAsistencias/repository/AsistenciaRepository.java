package com.proyecto.gestiorAsistencias.repository;

import com.proyecto.gestiorAsistencias.entities.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {

    //creamos el metodo para filtrar las asistencia por idEmpleado y fecha

    @Query("SELECT a FROM Asistencia a WHERE a.empleado.idEmpleado = :idEmpleado AND a.fecha = :fecha")
    List<Asistencia> findByEmpleadoIdAndFecha(@Param("idEmpleado") Long idEmpleado, @Param("fecha") LocalDate fecha);


    List<Asistencia> findByFecha(LocalDate fecha);
}
