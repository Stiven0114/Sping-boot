package com.stiven.Concesionario.app.servicio;


import com.stiven.Concesionario.app.entity.Vehiculo;

import java.util.List;

public interface VehiculoServicio {
    public List<Vehiculo> encontrarTodos();
    public Vehiculo encontrarporId(int id);
    public void actualizarVehiculo(Vehiculo vehiculo);
    public void crearVehiculo(Vehiculo vehiculo);
    public void eliminarVehiculo(int id);
}
