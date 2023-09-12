package com.stiven.Concesionario.app.negocio;


import com.stiven.Concesionario.app.dto.ConcesionarioDto;
import com.stiven.Concesionario.app.entity.Cliente;
import com.stiven.Concesionario.app.entity.Concesionario;
import com.stiven.Concesionario.app.implementacion.ConcesionarioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConcesionarioNegocio {
    @Autowired
    ConcesionarioImpl concesionarioImpl;

    private List<ConcesionarioDto> listaDtoConcesionario=new ArrayList<>();

    public List<ConcesionarioDto> encontrarTodos() {
        listaDtoConcesionario=new ArrayList<>();
        this.concesionarioImpl.encontrarTodos().forEach(concesionario -> {
            ConcesionarioDto concesionarioDto = new ConcesionarioDto();
            concesionarioDto.setId(concesionario.getId());
            concesionarioDto.setNombre(concesionario.getNombre());
            concesionarioDto.setDireccion(concesionario.getDireccion());
            concesionarioDto.setTelefono(concesionario.getTelefono());

            this.listaDtoConcesionario.add(concesionarioDto);
        });
        return this.listaDtoConcesionario;
    }

    public String guardarConcesionario (ConcesionarioDto concesionarioDto){
        Concesionario concesionario = new Concesionario();

        try{
            if (concesionarioDto.getId()!=0){
                concesionario.setId(concesionarioDto.getId());
                concesionario.setNombre(concesionarioDto.getNombre());
                concesionario.setDireccion(concesionarioDto.getDireccion());
                concesionario.setTelefono(concesionarioDto.getTelefono());

                this.concesionarioImpl.actualizarConcesionario(concesionario);
            }
            else {
                concesionario.setId(concesionarioDto.getId());
                concesionario.setNombre(concesionarioDto.getNombre());
                concesionario.setDireccion(concesionarioDto.getDireccion());
                concesionario.setTelefono(concesionarioDto.getTelefono());

                this.concesionarioImpl.crearConcesionario(concesionario);
            }
            return "Registro Exitoso";
        }
        catch(Exception e){
            return "Registro Fallido";
        }
    }

    public String eliminarConcesionario(int id){
        Concesionario concesionario;
        try{
            this.concesionarioImpl.eliminarConcesionario(id);
            return "Eliminacion exitosa";
        }catch (Exception e){
            e.printStackTrace();
            return "Eliminacion Fallida";
        }


    }

}


