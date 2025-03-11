package com.proyecto.gestiorAsistencias.controllers;

import com.proyecto.gestiorAsistencias.controllers.dtos.AsistenciaDTO;
import com.proyecto.gestiorAsistencias.service.IAsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/asistencia")
public class AsistenciaController {

    @Autowired
    private IAsistenciaService asistenciaService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarAsistencia(@RequestParam String dni){
        //verificamos si el dni no es nulo,
        if (dni == null || dni.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El DNI proporcionado no puede ser nulo o vacio.");
        }

        try {
            asistenciaService.registrarAsistencia(dni);
            return ResponseEntity.ok().body("Asistencia Registrada.");
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode())
                    .body(Map.of("error", ex.getReason()));
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll( @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha){

        List<AsistenciaDTO> listaAsistencia;

        if (fecha != null){
            listaAsistencia = asistenciaService.findByFecha(fecha);
        } else{
            listaAsistencia = asistenciaService.findAll();
        }
        return ResponseEntity.ok(listaAsistencia);
    }
}
