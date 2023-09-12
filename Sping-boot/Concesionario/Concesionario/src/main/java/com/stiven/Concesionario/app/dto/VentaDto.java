package com.stiven.Concesionario.app.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class VentaDto {
    private int id;

    private String metPago;

    private String nomCliente;
    private String apellCliente;
    private String nomEmpleado;
    private String apellEmpleado;
    private int precio;




}
