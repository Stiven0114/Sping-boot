package com.stiven.Concesionario.app.repositorio;

import com.stiven.Concesionario.app.entity.Concesionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface RepositoryConcesionario extends  JpaRepository <Concesionario,Integer>{
    @Query(value ="SELECT cl FROM Concesionario cl WHERE cl.id=id")
    public Concesionario encontrarporId(int id);
}