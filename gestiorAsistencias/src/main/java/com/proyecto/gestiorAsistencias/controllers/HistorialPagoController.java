package com.proyecto.gestiorAsistencias.controllers;

import com.proyecto.gestiorAsistencias.service.IHistorialPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/historial")
public class HistorialPagoController {

    @Autowired
    private IHistorialPagoService historialService;

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(historialService.findAll());
    }
}
