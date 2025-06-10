package com.proyecto.gestiorAsistencias.advice;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }

//    private String resourceName; //aqui recibo el nombre del campo que estoy solicitando
//    private String fieldName; // aqui recibo la referencia o campo que viene siendo el id
//    private Object fieldValue; // aqui recibo el valor del id que estoy consultando
//
//    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
//        super(String.format("%s no fue encontrado con: %s='%s'", resourceName,fieldName, fieldValue));
//        this.resourceName = resourceName;
//        this.fieldName = fieldName;
//        this.fieldValue = fieldValue;
//    }
}