package com.proyecto.gestiorAsistencias.controllers;

import com.proyecto.gestiorAsistencias.controllers.dtos.EmpleadoDTO;
import com.proyecto.gestiorAsistencias.entities.Empleado;
import com.proyecto.gestiorAsistencias.service.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/empleado")
public class EmpleadoController {

    @Autowired
    private IEmpleadoService empleadoService;

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){

        List<EmpleadoDTO> listaEmpleados = empleadoService.findAllEmpleado()
                .stream()
                .map(empleado -> EmpleadoDTO.builder()
                        .idEmpleado(empleado.getIdEmpleado())
                        .dni(empleado.getDni())
                        .nombreApellido(empleado.getNombreApellido())
                        .cargo(empleado.getCargo())
                        .horasLaboralesDiarias(empleado.getHorasLaboralesDiarias())
                        .costoPorHora(empleado.getCostoPorHora())
                        .build())
                .toList();

        return ResponseEntity.ok(listaEmpleados);

    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){

        Optional<Empleado> optionalEmpleado = empleadoService.findEmpleadoById(id);

        //verificamos si esta presente el empleado segun el id
        if (optionalEmpleado.isPresent()){
            Empleado empleado = optionalEmpleado.get(); //si existe obetenemos la entidad

            //convertimos al DTO usando el metodo build y seteamos los valores de la entidad al DTO
            EmpleadoDTO empleadoDTO = EmpleadoDTO.builder()
                    .idEmpleado(empleado.getIdEmpleado())
                    .dni(empleado.getDni())
                    .nombreApellido(empleado.getNombreApellido())
                    .cargo(empleado.getCargo())
                    .horasLaboralesDiarias(empleado.getHorasLaboralesDiarias())
                    .costoPorHora(empleado.getCostoPorHora())
                    .build();

            return ResponseEntity.ok(empleadoDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findByDni/{dni}")
    public ResponseEntity<?> findByDni(@PathVariable String dni){
        Optional<Empleado> optionalEmpleado = empleadoService.findByDni(dni);

        //verificamos si esta presente el empleado segun el id
        if (optionalEmpleado.isPresent()){
            Empleado empleado = optionalEmpleado.get(); //si existe obetenemos la entidad

            //convertimos al DTO usando el metodo build y seteamos los valores de la entidad al DTO
            EmpleadoDTO empleadoDTO = EmpleadoDTO.builder()
                    .idEmpleado(empleado.getIdEmpleado())
                    .dni(empleado.getDni())
                    .nombreApellido(empleado.getNombreApellido())
                    .cargo(empleado.getCargo())
                    .horasLaboralesDiarias(empleado.getHorasLaboralesDiarias())
                    .costoPorHora(empleado.getCostoPorHora())
                    .build();

            return ResponseEntity.ok(empleadoDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody @Validated EmpleadoDTO empleadoDTO) throws URISyntaxException {

        //creamos el objeto a partir del DTO y lo guardamos en la base de datos
        Empleado empleadoSaved = empleadoService.saveEmpleado(Empleado.builder()
                .dni(empleadoDTO.getDni())
                .nombreApellido(empleadoDTO.getNombreApellido())
                .cargo(empleadoDTO.getCargo())
                .horasLaboralesDiarias(empleadoDTO.getHorasLaboralesDiarias())
                .costoPorHora(empleadoDTO.getCostoPorHora())
                .horasTrabajadasPorMes(0.00)
                .sueldoMensual(BigDecimal.valueOf(0.00))
                .build());

        //Creo un DTO a partir del objeto para guardarlo en la respuesta
        EmpleadoDTO responseDTO = EmpleadoDTO.builder()
                .idEmpleado(empleadoSaved.getIdEmpleado())
                .dni(empleadoSaved.getDni())
                .nombreApellido(empleadoSaved.getNombreApellido())
                .cargo(empleadoSaved.getCargo())
                .horasLaboralesDiarias(empleadoSaved.getHorasLaboralesDiarias())
                .costoPorHora(empleadoSaved.getCostoPorHora())
                .build();

        //devolver el dto guardado en la respueta
        return ResponseEntity.created(new URI("/api/empleado/save/")).body(responseDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id ,@RequestBody EmpleadoDTO empleadoDTO) throws URISyntaxException {
        //buscamos el empleado mediante el id usando el metodo findById
        Optional<Empleado> optionalEmpleado = empleadoService.findEmpleadoById(id);

        //verificamos si se encuentra presente dicho empleado
        if (optionalEmpleado.isPresent()){
            Empleado empleado = optionalEmpleado.get(); //si existe lo recuperamos el objeto

            //seteamos los atributos del DTO a la entidad
            empleado.setDni(empleadoDTO.getDni());
            empleado.setNombreApellido(empleadoDTO.getNombreApellido());
            empleado.setCargo(empleadoDTO.getCargo());
            empleado.setHorasLaboralesDiarias(empleadoDTO.getHorasLaboralesDiarias());
            empleado.setCostoPorHora(empleadoDTO.getCostoPorHora());

            //lo guardamos usando el metodo save
            Empleado empleadoSaved = empleadoService.saveEmpleado(empleado);

            //Creo un DTO a partir del objeto para guardarlo en la respuesta
            EmpleadoDTO responseDTO = EmpleadoDTO.builder()
                    .idEmpleado(empleadoSaved.getIdEmpleado())
                    .dni(empleadoSaved.getDni())
                    .nombreApellido(empleadoSaved.getNombreApellido())
                    .cargo(empleadoSaved.getCargo())
                    .horasLaboralesDiarias(empleadoSaved.getHorasLaboralesDiarias())
                    .costoPorHora(empleadoSaved.getCostoPorHora())
                    .build();

            //retronamos la respuesta pasandole el objeto nuevo
            return ResponseEntity.ok(responseDTO);
        }

        //si no esta presente el objeto, retornamos notFound
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        //verificamos si el id no es nulo,
        if (id != null){
            //si el id es correcto, procemos a eliminar el objeto
            empleadoService.deleteEmpleadoById(id);
            return ResponseEntity.ok("Registro Eliminado");
        }

        return ResponseEntity.badRequest().build();
    }
}
