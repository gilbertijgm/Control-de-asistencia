package com.proyecto.gestiorAsistencias.service;

import com.proyecto.gestiorAsistencias.entities.Empleado;
import com.proyecto.gestiorAsistencias.entities.HistorialPagos;
import com.proyecto.gestiorAsistencias.persistence.IEmpleadoDAO;
import com.proyecto.gestiorAsistencias.repository.HistorialPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FinDeMesService {

    @Autowired
    private IEmpleadoDAO iEmpleadoDAO;

    @Autowired
    private HistorialPagoRepository historialPagoRepository;

    @Transactional
    public void procesarFinDeMes(){

        //obtengo fecha de mes que cerro
        /*
        * LocalDate.now() → Obtiene la fecha actual (por ejemplo, si hoy es 1 de marzo de 2025).
            .minusMonths(1) → Resta un mes (queda 1 de febrero de 2025).
.           withDayOfMonth(1) → Asegura que el día sea 1, lo que marca el inicio del mes cerrado.
        * */
        LocalDate fechaMesCerrado = LocalDate.now().minusMonths(1).withDayOfMonth(1);

        //obtengo la fecha de generacion del pago
        LocalDateTime fechaDeGeneracion = LocalDateTime.now();

        List<Empleado> empleados = iEmpleadoDAO.findAllEmpleado();

        for (Empleado empleado: empleados){
            //guardar en historial de pago
            HistorialPagos pago = new HistorialPagos();

            //Seteamos los valores de cada empleado que tengan relacion con la entidad historial
            pago.setEmpleado(empleado);
            pago.setFechaMes(fechaMesCerrado);
            pago.setHorasTrabajadas(empleado.getHorasTrabajadasPorMes());
            pago.setSueldoPagado(BigDecimal.valueOf(empleado.getSueldoMensual()));
            pago.setFechaDeGeneracion(fechaDeGeneracion);

            historialPagoRepository.save(pago);

            //Reiniciamos los campos en Empleado
            empleado.setHorasTrabajadasPorMes((double) 0L);
            empleado.setSueldoMensual(0.0);

            iEmpleadoDAO.saveEmpleado(empleado);

        }
    }
}
