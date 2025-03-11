package com.proyecto.gestiorAsistencias.controllers;

import com.proyecto.gestiorAsistencias.service.FinDeMesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/finDeMes")
public class FinDeMesController {

    @Autowired
    private FinDeMesService finDeMesService;

    @PostMapping("/procesar")
    public ResponseEntity<?> procesarFinDeMes(){

        finDeMesService.procesarFinDeMes();
        return ResponseEntity.ok("Cierre de mes procesado con exito.");
    }
}
