package com.stiven.Concesionario.app.implementacion;


import com.stiven.Concesionario.app.entity.Venta;
import com.stiven.Concesionario.app.repositorio.RepositoryVenta;
import com.stiven.Concesionario.app.servicio.VentaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaImpl implements VentaServicio {
    @Autowired
    RepositoryVenta repositoryVenta;

    @Override
    public List<Venta> encontrarTodos() {
        return this.repositoryVenta.findAll();
    }

    @Override
    public Venta encontrarporId(int id) {
        Venta venta = this.repositoryVenta.encontrarporId(id);
        return venta;
    }

    @Override
    public void actualizarVenta(Venta venta) {
        if (venta.getId()!=0){
            this.repositoryVenta.save(venta);
        }

    }

    @Override
    public void crearVenta(Venta venta) {
        this.repositoryVenta.save(venta);
    }

    @Override
    public void eliminarVenta(int id) {
        System.out.println("######"+id);
        Venta venta = this.repositoryVenta.getById(id);
        System.out.println("@@@@@@"+venta.toString());


        if (venta != null){
            this.repositoryVenta.delete(venta);
        }

    }
}
