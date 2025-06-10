package com.proyecto.gestiorAsistencias.service.implementacion;

import com.proyecto.gestiorAsistencias.advice.ResourceNotFoundException;
import com.proyecto.gestiorAsistencias.controllers.dtos.EmpleadoDTO;
import com.proyecto.gestiorAsistencias.entities.Empleado;
import com.proyecto.gestiorAsistencias.persistence.IEmpleadoDAO;
import com.proyecto.gestiorAsistencias.service.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



import java.util.List;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

    @Autowired
    private IEmpleadoDAO empleadoDAO;

    @Override
    public  Page<EmpleadoDTO> findAllEmpleadoPage(Pageable pageable) {
        // Obtener la lista de empleados paginados desde el repositorio
        Page<Empleado> empleadosPage = empleadoDAO.findAllEmpleadoPage(pageable);

        // Convertir cada empleado a EmpleadoDTO usando .map()
        return empleadosPage.map(empleado -> EmpleadoDTO.builder()
                .idEmpleado(empleado.getIdEmpleado())
                .dni(empleado.getDni())
                .nombreApellido(empleado.getNombreApellido())
                .cargo(empleado.getCargo())
                .horasLaboralesDiarias(empleado.getHorasLaboralesDiarias())
                .costoPorHora(empleado.getCostoPorHora())
                .horasTrabajadasPorMes(empleado.getHorasTrabajadasPorMes())
                .sueldoMensual(empleado.getSueldoMensual())
                .build());
    }

    @Override
    public List<EmpleadoDTO> findAllEmpleado() {
        List<EmpleadoDTO> listaEmpleados = empleadoDAO.findAllEmpleado().stream()
                .map(empleado -> EmpleadoDTO.builder()
                        .idEmpleado(empleado.getIdEmpleado())
                        .dni(empleado.getDni())
                        .nombreApellido(empleado.getNombreApellido())
                        .cargo(empleado.getCargo())
                        .horasLaboralesDiarias(empleado.getHorasLaboralesDiarias())
                        .costoPorHora(empleado.getCostoPorHora())
                        .build())
                .toList();

        return listaEmpleados;
    }

    @Override
    public EmpleadoDTO findEmpleadoById(Long id) {

        return empleadoDAO.findEmpleadoById(id)
                .map(empleado -> EmpleadoDTO.builder()
                        .idEmpleado(empleado.getIdEmpleado())
                        .dni(empleado.getDni())
                        .nombreApellido(empleado.getNombreApellido())
                        .cargo(empleado.getCargo())
                        .horasLaboralesDiarias(empleado.getHorasLaboralesDiarias())
                        .costoPorHora(empleado.getCostoPorHora())
                        .build())
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no econtrado con el id: " + id));
    }

    @Override
    public EmpleadoDTO saveEmpleado(EmpleadoDTO empleadoDto) {
        //Convierto el DTO que recibo a una entidad
        Empleado empleado = Empleado.builder()
                .dni(empleadoDto.getDni())
                .nombreApellido(empleadoDto.getNombreApellido())
                .cargo(empleadoDto.getCargo())
                .horasLaboralesDiarias(empleadoDto.getHorasLaboralesDiarias())
                .costoPorHora(empleadoDto.getCostoPorHora())
                .horasTrabajadasPorMes(empleadoDto.getHorasTrabajadasPorMes())
                .sueldoMensual(empleadoDto.getSueldoMensual())
                .build();

        //guardo el empleado convertido en la bd
        empleado = empleadoDAO.saveEmpleado(empleado);

        //para retornar la respuesta, primero debo convertir el empleado guardado de nuevo a un DTO
        return EmpleadoDTO.builder()
                .idEmpleado(empleado.getIdEmpleado())
                .dni(empleado.getDni())
                .nombreApellido(empleado.getNombreApellido())
                .cargo(empleado.getCargo())
                .horasLaboralesDiarias(empleado.getHorasLaboralesDiarias())
                .costoPorHora(empleado.getCostoPorHora())
                .build();
    }

    @Override
    public EmpleadoDTO updateEmpleado(EmpleadoDTO empleadoDto, Long id) {
        Empleado empleado = empleadoDAO.findEmpleadoById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con el id: " + id));

        //Actualizamos los campos de la entidad con los atributos del DTO que recibimos
        empleado.setDni(empleadoDto.getDni());
        empleado.setNombreApellido(empleadoDto.getNombreApellido());
        empleado.setCargo(empleadoDto.getCargo());
        empleado.setHorasLaboralesDiarias(empleadoDto.getHorasLaboralesDiarias());
        empleado.setCostoPorHora(empleadoDto.getCostoPorHora());

        //Guardamos la entidad actualizada
        Empleado updateEmpleado = empleadoDAO.saveEmpleado(empleado);

        //convertimos la entidad actualizada a DTO para la respuesta
        return EmpleadoDTO.builder()
                .idEmpleado(updateEmpleado.getIdEmpleado())
                .dni(updateEmpleado.getDni())
                .nombreApellido(updateEmpleado.getNombreApellido())
                .cargo(updateEmpleado.getCargo())
                .horasLaboralesDiarias(updateEmpleado.getHorasLaboralesDiarias())
                .costoPorHora(updateEmpleado.getCostoPorHora())
                .build();
    }


    @Override
    public void deleteEmpleadoById(Long id) {
        Empleado empleado = empleadoDAO.findEmpleadoById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con el id: " + id));

        empleadoDAO.deleteEmpleadoById(empleado.getIdEmpleado());
    }

    @Override
    public EmpleadoDTO findByDni(String dni) {
        Empleado empleado = empleadoDAO.findByDni(dni)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con el DNI: " + dni));

        //convertimos la enditad a un DTO para la respuesta
        return EmpleadoDTO.builder()
                .idEmpleado(empleado.getIdEmpleado())
                .dni(empleado.getDni())
                .nombreApellido(empleado.getNombreApellido())
                .cargo(empleado.getCargo())
                .horasLaboralesDiarias(empleado.getHorasLaboralesDiarias())
                .costoPorHora(empleado.getCostoPorHora())
                .build();
    }
}
