package com.proyecto.gestiorAsistencias.service.implementacion;

import com.proyecto.gestiorAsistencias.advice.ResourceNotFoundException;
import com.proyecto.gestiorAsistencias.controllers.dtos.AsistenciaDTO;
import com.proyecto.gestiorAsistencias.entities.Asistencia;
import com.proyecto.gestiorAsistencias.entities.Empleado;
import com.proyecto.gestiorAsistencias.persistence.IAsistenciaDAO;
import com.proyecto.gestiorAsistencias.persistence.IEmpleadoDAO;
import com.proyecto.gestiorAsistencias.repository.AsistenciaRepository;
import com.proyecto.gestiorAsistencias.service.IAsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class AsistenciaServiceImpl implements IAsistenciaService {

    @Autowired
    private IAsistenciaDAO asistenciaDAO;

    @Autowired
    private IEmpleadoDAO empleadoDAO;

    @Override
    public Asistencia registrarAsistencia(String dni) {
        //buscamos empleado por dni
        Empleado empleado = empleadoDAO.findByDni(dni)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con el DNI: " + dni) );

        //obtenemos la fecha y hora actual automaticamente
        LocalDate today = LocalDate.now();
        LocalTime hourToday = LocalTime.now();

        //obtenemos registro de asistencia del dia actual
        List<Asistencia> registrosDelDia = asistenciaDAO.registrosDelDia(empleado.getIdEmpleado(), today);

        //Verificamos si hay asistencias registradas
        if (registrosDelDia.isEmpty()){
            //si no hay asistencia registradas, se procede a guardar la asistencia de entrada
            Asistencia entrada = new Asistencia();
            entrada.setEmpleado(empleado);
            entrada.setFecha(today);
            entrada.setHora(hourToday);
            entrada.setTipoDeRegistro(Asistencia.TipoRegistro.Entrada);

            return asistenciaDAO.registrarAsistencia(entrada);
        } else if (registrosDelDia.size() == 1 && Asistencia.TipoRegistro.Entrada.equals(registrosDelDia.get(0).getTipoDeRegistro())){
            //si ya hay una entrada previa registrada, registramos la salida

            //primero seteamos los valores a registrar
            Asistencia salida = new Asistencia();
            salida.setEmpleado(empleado);
            salida.setFecha(today);
            salida.setHora(hourToday);
            salida.setTipoDeRegistro(Asistencia.TipoRegistro.Salida);

            /*Ya que tenemos una salida, hacemos el calculo de las horas trabajadas
             desde que se registro la hora de entrada hasta la hora de salida*/

            LocalTime horaEntrada = registrosDelDia.get(0).getHora(); //obtenemos la hora de entrada
            LocalTime horaSalida = salida.getHora(); //obtenemos la hora de salida
            //hacemos el calculo del total de horas que se trabajo dadas las hora de entrada y salida
            long horasTrabajadas = Duration.between(horaEntrada,horaSalida).toHours();

            //Seteamos las horas trabajadas a la entidad empleado que es a la que corresponde, y se la sumo a las horas que ya tiene registradas
            empleado.setHorasTrabajadasPorMes(empleado.getHorasTrabajadasPorMes() + horasTrabajadas);

            //Ya que tenemos las horas trabajadas podemos ir calculando el sueldo ganado segun las horas e ir acumulando en sueldoMensual
            double sueldoGanado = horasTrabajadas * empleado.getCostoPorHora();
            empleado.setSueldoMensual(empleado.getSueldoMensual() + sueldoGanado);


            //guardo los cambios del empleado
            empleadoDAO.saveEmpleado(empleado);

            //por ultimo se guarda el registro de salida
            return asistenciaDAO.registrarAsistencia(salida);
        } else {
            //en el caso de que ya haya una entrada y una salida registrada se le notifica al usuario
            throw new IllegalArgumentException("El empleado ya tiene registrada una entrada y salida en el dia de hoy.");
        }
    }

    @Override
    public List<AsistenciaDTO> findAll() {
        List<AsistenciaDTO> listaAsistencias = asistenciaDAO.findAll()
                .stream()
                .map(asistencia -> AsistenciaDTO.builder()
                        .idAsistencia(asistencia.getIdAsistencia())
                        .fecha(asistencia.getFecha())
                        .hora(asistencia.getHora())
                        .tipoDeRegistro(asistencia.getTipoDeRegistro())
                        .empleado(asistencia.getEmpleado())
                        .build()
                )
                .toList();

        return listaAsistencias;
    }

    @Override
    public List<AsistenciaDTO> findByFecha(LocalDate fecha) {
        List<AsistenciaDTO> listaAsistencias = asistenciaDAO.findByFecha(fecha)
                .stream()
                .map(asistencia -> AsistenciaDTO.builder()
                        .idAsistencia(asistencia.getIdAsistencia())
                        .fecha(asistencia.getFecha())
                        .hora(asistencia.getHora())
                        .tipoDeRegistro(asistencia.getTipoDeRegistro())
                        .empleado(asistencia.getEmpleado())
                        .build()
                )
                .toList();

        return listaAsistencias;
    }
}
