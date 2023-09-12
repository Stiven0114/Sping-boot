package com.stiven.Concesionario.app.repositorio;


import com.stiven.Concesionario.app.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryVenta extends JpaRepository<Venta,Integer> {
    @Query(value = "SELECT e FROM Venta e WHERE e.id=id")
    public Venta encontrarporId(int id);
}
