package com.stiven.Concesionario.app.negocio;

import com.stiven.Concesionario.app.dto.VentaDto;
import com.stiven.Concesionario.app.entity.Venta;
import com.stiven.Concesionario.app.implementacion.VentaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VentaNegocio {

    @Autowired
    VentaImpl ventaImpl;

    private List<VentaDto> listaDtoVentaDto=new ArrayList<>();

    public List<VentaDto> encontrarTodos() {
        listaDtoVentaDto=new ArrayList<>();
        this.ventaImpl.encontrarTodos().forEach(venta -> {
            VentaDto ventaDto = new VentaDto();
            ventaDto.setId(venta.getId());

            ventaDto.setMetPago(venta.getMetPago());

            ventaDto.setNomCliente(venta.getNomCliente());
            ventaDto.setApellCliente(venta.getApellCliente());
            ventaDto.setNomEmpleado(venta.getNomEmpleado());
            ventaDto.setApellEmpleado(venta.getApellEmpleado());
            ventaDto.setPrecio(venta.getPrecio());

            this.listaDtoVentaDto.add(ventaDto);
        });
        return this.listaDtoVentaDto;
    }

    public String guardarVenta (VentaDto ventaDto){
        Venta venta = new Venta();

        try{
            if (ventaDto.getId()!=0){
                venta.setId(ventaDto.getId());

                venta.setMetPago(ventaDto.getMetPago());

                venta.setNomCliente(ventaDto.getNomCliente());
                venta.setApellCliente(ventaDto.getApellCliente());
                venta.setNomEmpleado(ventaDto.getNomEmpleado());
                venta.setApellEmpleado(ventaDto.getApellEmpleado());
                venta.setPrecio(ventaDto.getPrecio());

                this.ventaImpl.actualizarVenta(venta);
            }
            else {
                venta.setId(ventaDto.getId());

                venta.setMetPago(ventaDto.getMetPago());

                venta.setNomCliente(ventaDto.getNomCliente());
                venta.setApellCliente(ventaDto.getApellCliente());
                venta.setNomEmpleado(ventaDto.getNomEmpleado());
                venta.setApellEmpleado(ventaDto.getApellEmpleado());
                venta.setPrecio(ventaDto.getPrecio());

                this.ventaImpl.crearVenta(venta);
            }
            return "Registro Exitoso";
        }
        catch(Exception e){
            return "Registro Fallido";
        }
    }

    public String eliminar(int id){
        Venta venta;
        try{
            this.ventaImpl.eliminarVenta(id);
            return "Eliminacion exitosa";
        }catch (Exception e){
            e.printStackTrace();
            return "Eliminacion Fallida";
        }


    }


}

