package com.stiven.Concesionario.app.negocio;

import com.stiven.Concesionario.app.dto.VehiculoDto;
import com.stiven.Concesionario.app.entity.Vehiculo;

import com.stiven.Concesionario.app.implementacion.VehiculoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VehiculoNegocio {

    @Autowired
    VehiculoImpl vehiculoImpl;

    private List<VehiculoDto> listaDtoVehiculos=new ArrayList<>();

    public List<VehiculoDto> encontrarTodos() {
        listaDtoVehiculos=new ArrayList<>();
        this.vehiculoImpl.encontrarTodos().forEach(vehiculo -> {
            VehiculoDto vehiculoDto = new VehiculoDto();
            vehiculoDto.setId(vehiculo.getId());
            vehiculoDto.setTipoVehiculo(vehiculo.getTipoVehiculo());
            vehiculoDto.setModelo(vehiculo.getModelo());
            vehiculoDto.setCentCubicos(vehiculo.getCentCubicos());
            vehiculoDto.setPlaca(vehiculo.getPlaca());
            vehiculoDto.setPrecio(vehiculo.getPrecio());
            vehiculoDto.setMarca(vehiculo.getMarca());
            vehiculoDto.setColor(vehiculo.getColor());
            this.listaDtoVehiculos.add(vehiculoDto);
        });
        return this.listaDtoVehiculos;
    }

    public String guardarVehiculo (VehiculoDto vehiculoDto){
        Vehiculo vehiculo = new Vehiculo();

        try{
            if (vehiculoDto.getId()!=0){
                vehiculo.setId(vehiculoDto.getId());
                vehiculo.setTipoVehiculo(vehiculoDto.getTipoVehiculo());
                vehiculo.setModelo(vehiculoDto.getModelo());
                vehiculo.setCentCubicos(vehiculoDto.getCentCubicos());
                vehiculo.setPlaca(vehiculoDto.getPlaca());
                vehiculo.setPrecio(vehiculoDto.getPrecio());
                vehiculo.setMarca(vehiculoDto.getMarca());
                vehiculo.setColor(vehiculoDto.getColor());
                this.vehiculoImpl.actualizarVehiculo(vehiculo);
            }
            else {
                vehiculo.setId(vehiculoDto.getId());
                vehiculo.setTipoVehiculo(vehiculoDto.getTipoVehiculo());
                vehiculo.setModelo(vehiculoDto.getModelo());
                vehiculo.setCentCubicos(vehiculoDto.getCentCubicos());
                vehiculo.setPlaca(vehiculoDto.getPlaca());
                vehiculo.setPrecio(vehiculoDto.getPrecio());
                vehiculo.setMarca(vehiculoDto.getMarca());
                vehiculo.setColor(vehiculoDto.getColor());
                this.vehiculoImpl.crearVehiculo(vehiculo);
            }
            return "Registro Exitoso";
        }
        catch(Exception e){
            return "Registro Fallido";
        }
    }

    public String eliminar(int id){
        Vehiculo vehiculo;
        try{
            this.vehiculoImpl.eliminarVehiculo(id);
            return "Eliminacion exitosa";
        }catch (Exception e){
            e.printStackTrace();
            return "Eliminacion Fallida";
        }


    }


}

