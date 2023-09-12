package com.stiven.Concesionario.app.dto;

import lombok.Data;

@Data
public class VehiculoDto {
    private int id;
    private String tipoVehiculo;
    private int modelo;
    private int centCubicos;
    private String placa;
    private int precio;
    private String marca;
    private String color;
}
