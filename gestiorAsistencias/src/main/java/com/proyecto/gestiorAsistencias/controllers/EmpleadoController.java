package com.proyecto.gestiorAsistencias.controllers;

import com.proyecto.gestiorAsistencias.controllers.dtos.EmpleadoDTO;
import com.proyecto.gestiorAsistencias.entities.Empleado;
import com.proyecto.gestiorAsistencias.service.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/empleado")
public class EmpleadoController {

    @Autowired
    private IEmpleadoService empleadoService;

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){

        return ResponseEntity.ok(empleadoService.findAllEmpleado());

    }

    @GetMapping("/findById")
    public ResponseEntity<?> findById(@RequestParam(required = false) Long id) throws ResponseStatusException {
        //verificamos si el id no es nulo,
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID proporcionado no puede ser nulo o vacío.");
        }

        try {

            return ResponseEntity.ok(empleadoService.findEmpleadoById(id));
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID proporcionado no es válido.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/findByDni")
    public ResponseEntity<?> findByDni(@RequestParam(required = false) String dni){
        //verificamos si el dni no es nulo,
        if (dni == null || dni.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El DNI proporcionado no puede ser nulo o vacio.");
        }

        try {
            return ResponseEntity.ok(empleadoService.findByDni(dni));
        } catch (NoSuchElementException e) { //excepción en Java que se produce cuando no se encuentra el elemento solicitado
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody @Validated EmpleadoDTO empleadoDTO) throws URISyntaxException {

        EmpleadoDTO savedEmpleado = empleadoService.saveEmpleado(empleadoDTO);

        return ResponseEntity.created(new URI("/api/empleado/save/")).body(savedEmpleado);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam(required = false) Long id ,@RequestBody EmpleadoDTO empleadoDTO) throws URISyntaxException {
        //verificamos si el id no es nulo,
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID proporcionado no puede ser nulo o vacío.");
        }

        try {
            return ResponseEntity.ok(empleadoService.updateEmpleado(empleadoDTO, id));
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID proporcionado no es válido.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteById(@RequestParam(required = false) Long id){
        //verificamos si el id no es nulo,
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID proporcionado no puede ser nulo o vacío.");
        }

        try {
            empleadoService.deleteEmpleadoById(id);
            return ResponseEntity.ok("Registro Eliminado");
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID proporcionado no es válido.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
