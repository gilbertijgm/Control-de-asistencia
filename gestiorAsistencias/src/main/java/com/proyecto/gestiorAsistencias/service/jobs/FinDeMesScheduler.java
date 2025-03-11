package com.proyecto.gestiorAsistencias.service.jobs;

import com.proyecto.gestiorAsistencias.service.FinDeMesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FinDeMesScheduler {

    @Autowired
    private FinDeMesService finDeMesService;


    @Scheduled(cron = "0 0 0 1 * ?") //se ejecuta automaticamente a las 00:00 del primer dia del mes
    public void ejecutarCierreMensual(){

        finDeMesService.procesarFinDeMes();
    }
}
