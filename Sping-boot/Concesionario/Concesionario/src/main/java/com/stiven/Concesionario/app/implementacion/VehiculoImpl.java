package com.stiven.Concesionario.app.implementacion;


import com.stiven.Concesionario.app.entity.Vehiculo;
import com.stiven.Concesionario.app.repositorio.RepositoryVehiculo;
import com.stiven.Concesionario.app.servicio.VehiculoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoImpl implements VehiculoServicio {
    @Autowired
    RepositoryVehiculo repositoryVehiculo;
    @Override
    public List<Vehiculo> encontrarTodos() {
        return this.repositoryVehiculo.findAll();
    }

    @Override
    public Vehiculo encontrarporId(int id) {
        Vehiculo vehiculo =  this.repositoryVehiculo.encontrarporId(id);
        return vehiculo;
    }

    @Override
    public void actualizarVehiculo(Vehiculo vehiculo) {
        if (vehiculo.getId()!=0){
            this.repositoryVehiculo.save(vehiculo);
        }
    }

    @Override
    public void crearVehiculo(Vehiculo empleado) {
        this.repositoryVehiculo.save(empleado);
    }

    @Override
    public void eliminarVehiculo(int id) {
        System.out.println("######"+id);
        Vehiculo empleado = this.repositoryVehiculo.getById(id);
        System.out.println("@@@@@@"+ empleado.toString());


        if (empleado != null){
            this.repositoryVehiculo.delete(empleado);
        }

    }
}

