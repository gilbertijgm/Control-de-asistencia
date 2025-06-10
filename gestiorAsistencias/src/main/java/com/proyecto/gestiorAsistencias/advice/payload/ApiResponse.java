package com.proyecto.gestiorAsistencias.advice.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@Builder
public class ApiResponse<T> {

    private int statusCode;
    private String message;
    private T object;
}
