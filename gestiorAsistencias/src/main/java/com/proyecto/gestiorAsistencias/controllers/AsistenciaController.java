package com.proyecto.gestiorAsistencias.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/asistencia")
public class AsistenciaController {

    @PostMapping("/registrar/{dni}")
    public ResponseEntity<?> registrarAsistencia(@PathVariable String dni){

    }
}
