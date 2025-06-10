package com.proyecto.gestiorAsistencias.controllers;

import com.proyecto.gestiorAsistencias.advice.payload.ApiResponse;
import com.proyecto.gestiorAsistencias.controllers.dtos.EmpleadoDTO;
import com.proyecto.gestiorAsistencias.service.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/empleado")
public class EmpleadoController {

    @Autowired
    private IEmpleadoService empleadoService;

    @GetMapping("/findAll")
    public ResponseEntity<ApiResponse<Map<String, Object>>> findAll(@RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "5") int size){

        // crear el pageable para la paginacion
        Pageable pageable = PageRequest.of(page, size);

        // obtener empleados paginados
        Page<EmpleadoDTO> empleadoPage = empleadoService.findAllEmpleadoPage(pageable);

        // creamos un mapa para almacenar las respuestas personalizadas
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("empleados", empleadoPage.getContent());
        response.put("totalElements", empleadoPage.getTotalElements()); //Devuelve el total de elementos
        response.put("totalPages", empleadoPage.getTotalPages()); // obtiene el total de paginas
        response.put("pageSize", empleadoPage.getSize()); //devuelve el tamaño de la pagina

        //Calculando y obteniendo la pagina anterior
        response.put("previousPage", empleadoPage.hasPrevious() ? empleadoPage.getNumber() - 1 : null); // si empleadoPage.hasPrevious() es true restamos uno a la pagina actual, si no hay pagina anterior devolvemos null

        response.put("currentPage", empleadoPage.getNumber()); // obtiene el numero actual de la pagina

        //Calculando y obteniendo la pagina siguiente
       response.put("nextPage", empleadoPage.hasNext() ? empleadoPage.getNumber() + 1 : null); //si empleadoPage.hasNext() es true sumamos uno a la pagina actual, si no hay pagina anterior devolvemos null

        ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<>(200,
                "Lista de empleados obetenidad correctamente", response);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/findById")
    public ResponseEntity<?> findById(@RequestParam(required = false) Long id) throws ResponseStatusException {
        //verificamos si el id no es nulo,
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID proporcionado no puede ser nulo o vacío.");
        }

        try {
            EmpleadoDTO empleado = empleadoService.findEmpleadoById(id);

            ApiResponse<EmpleadoDTO> response = new ApiResponse<>(201, "Recurso Encontrado", empleado);
            return ResponseEntity.ok(response);
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
            EmpleadoDTO empleado = empleadoService.findByDni(dni);

            ApiResponse<EmpleadoDTO> response = new ApiResponse<>(201, "Recurso Encontrado", empleado);
            return ResponseEntity.ok(response);
        } catch (NoSuchElementException e) { //excepción en Java que se produce cuando no se encuentra el elemento solicitado
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody @Validated EmpleadoDTO empleadoDTO) throws URISyntaxException {

        EmpleadoDTO savedEmpleado = empleadoService.saveEmpleado(empleadoDTO);

        ApiResponse<EmpleadoDTO> response = new ApiResponse<>(201, "Registrado Correctamente", savedEmpleado);

        return ResponseEntity.created(new URI("/api/empleado/save/")).body(response);

    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam(required = false) Long id ,@RequestBody EmpleadoDTO empleadoDTO) throws URISyntaxException {
        //verificamos si el id no es nulo,
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID proporcionado no puede ser nulo o vacío.");
        }

        try {
            EmpleadoDTO updateEmpleado = empleadoService.updateEmpleado(empleadoDTO, id);

            ApiResponse<EmpleadoDTO> response = new ApiResponse<>(200, "Actualizado Correctamente", updateEmpleado);
            return ResponseEntity.ok(response);
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

            ApiResponse<EmpleadoDTO> response = new ApiResponse<>(200, "Recurso Eliminado", null);
            return ResponseEntity.ok(response);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID proporcionado no es válido.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
