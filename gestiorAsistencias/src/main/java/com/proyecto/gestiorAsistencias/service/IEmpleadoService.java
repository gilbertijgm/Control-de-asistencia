package com.proyecto.gestiorAsistencias.service;

import com.proyecto.gestiorAsistencias.controllers.dtos.EmpleadoDTO;

import java.util.List;

public interface IEmpleadoService {

    List<EmpleadoDTO> findAllEmpleado();

    //Optional me permite manejar los casos en que los valores sean nulos
    EmpleadoDTO findEmpleadoById(Long id);

    EmpleadoDTO saveEmpleado(EmpleadoDTO empleadoDto);

    EmpleadoDTO updateEmpleado(EmpleadoDTO empleadoDto, Long id);

    void deleteEmpleadoById(Long id);

    EmpleadoDTO findByDni(String dni);
}
