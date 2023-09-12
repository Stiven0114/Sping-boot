package com.stiven.Concesionario.app.negocio;

import com.stiven.Concesionario.app.dto.EmpleadoDto;
import com.stiven.Concesionario.app.entity.Empleado;
import com.stiven.Concesionario.app.implementacion.EmpleadoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmpleadoNegocio {

    @Autowired
    EmpleadoImpl empleadoImpl;

    private List<EmpleadoDto> listaDtoEmpleado=new ArrayList<>();

    public List<EmpleadoDto> encontrarTodos() {
        listaDtoEmpleado=new ArrayList<>();
        this.empleadoImpl.encontrarTodos().forEach(empleado -> {
            EmpleadoDto empleadoDto = new EmpleadoDto();
            empleadoDto.setId(empleado.getId());
            empleadoDto.setNombres(empleado.getNombres());
            empleadoDto.setApellidos(empleado.getApellidos());
            empleadoDto.setTelefono(empleado.getTelefono());
            empleadoDto.setCorreo(empleado.getCorreo());
            this.listaDtoEmpleado.add(empleadoDto);
        });
        return this.listaDtoEmpleado;
    }

    public String guardarEmpleado (EmpleadoDto empleadoDto){
        Empleado empleado = new Empleado();

        try{
            if (empleadoDto.getId()!=0){
                empleado.setId(empleadoDto.getId());
                empleado.setNombres(empleadoDto.getNombres());
                empleado.setApellidos(empleadoDto.getApellidos());
                empleado.setTelefono(empleadoDto.getTelefono());
                empleado.setCorreo(empleadoDto.getCorreo());
                this.empleadoImpl.actualizarEmpleado(empleado);
            }
            else {
                empleado.setId(empleadoDto.getId());
                empleado.setNombres(empleadoDto.getNombres());
                empleado.setApellidos(empleadoDto.getApellidos());
                empleado.setTelefono(empleadoDto.getTelefono());
                empleado.setCorreo(empleadoDto.getCorreo());
                this.empleadoImpl.crearEmpleado(empleado);
            }
            return "Registro Exitoso";
        }
        catch(Exception e){
            return "Registro Fallido";
        }
    }

    public String eliminarEmpleado(int id){
        Empleado empleado;
        try{
            this.empleadoImpl.eliminarEmpleado(id);
            return "Eliminacion exitosa";
        }catch (Exception e){
            e.printStackTrace();
            return "Eliminacion Fallida";
        }


    }


}

