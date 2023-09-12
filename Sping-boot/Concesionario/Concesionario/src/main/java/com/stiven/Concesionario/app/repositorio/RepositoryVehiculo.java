package com.stiven.Concesionario.app.repositorio;


import com.stiven.Concesionario.app.entity.Vehiculo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryVehiculo extends JpaRepository <Vehiculo,Integer> {
    @Query(value = "SELECT e FROM Vehiculo e WHERE e.id=id")
    public Vehiculo encontrarporId(int id);

}
