package com.proyecto.gestiorAsistencias.advice;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /*
     * crear un metodo de tipo map, porque vamos a devolver un json y la estructura del json es un mapa clave valor
     * */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArguments(MethodArgumentNotValidException exception){
        //defino el mapa
        Map<String, String> errors = new HashMap<>();

        /*
         * getBindingResult() muestra el resultado de la exception
         * getFieldErrors() muestra los errors de todos los campos
         * */
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            //con put le seteamos todos los campos(clave) y el mensage(valor) al mapa
            errors.put(error.getField(), error.getDefaultMessage());
        });

        //retorno el mapa errors
        return errors;
    }
}
