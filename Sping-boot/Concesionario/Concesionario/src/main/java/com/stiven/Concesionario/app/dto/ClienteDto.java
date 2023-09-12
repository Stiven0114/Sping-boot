package com.stiven.Concesionario.app.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ClienteDto {

    private int id;
    private String nombres;
    private String apellidos;
    private Long telefono;
    private String correo;
}
