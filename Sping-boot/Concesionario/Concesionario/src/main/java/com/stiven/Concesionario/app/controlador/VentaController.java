package com.stiven.Concesionario.app.controlador;

import com.stiven.Concesionario.app.dto.VentaDto;
import com.stiven.Concesionario.app.entity.Venta;
import com.stiven.Concesionario.app.implementacion.VentaImpl;
import com.stiven.Concesionario.app.negocio.VentaNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/Venta")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE,RequestMethod.HEAD})
public class VentaController {

    @Autowired
    VentaImpl ventaimpl;

    @Autowired
    VentaNegocio ventaNegocio;

    @Autowired
    VentaDto ventaDto;



    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<Map<String,Object>> encontrarTodos(){
        List<VentaDto> listaVenta= this.ventaNegocio.encontrarTodos();
        Map<String,Object> res=new HashMap<>();
        res.put("status","ok");
        res.put("data",listaVenta);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/crear")
    @ResponseBody
    public ResponseEntity <Map<String,Object>> crearVenta(@RequestBody Map<String,Object> request){
        Map<String,Object> res=new HashMap<>();
        try{
            System.out.println("@@@@@@"+request.toString());
            VentaDto ventaDto=new VentaDto();
            ventaDto.setId(0);

            ventaDto.setMetPago(request.get("metPago").toString());

            ventaDto.setNomCliente(request.get("nomCliente").toString());
            ventaDto.setApellCliente(request.get("apellCliente").toString());
            ventaDto.setNomEmpleado(request.get("nomEmpleado").toString());
            ventaDto.setApellEmpleado(request.get("apellEmpleado").toString());
            ventaDto.setPrecio(Integer.parseInt(request.get("precio").toString()));

            String resp = this.ventaNegocio.guardarVenta(ventaDto);
            res.put("status","ok");
            res.put("code","200");
            res.put("data",resp);
        }catch (Exception e){
            res.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
            res.put("code","500");
            res.put("data",e.getMessage());
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/Actualizar")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> actualizarVenta(@RequestBody Map<String, Object> request) {
        Map<String, Object> res = new HashMap<>();

        ventaDto.setId(Integer.parseInt(request.get("id").toString()));
        ventaDto.setMetPago(request.get("metPago").toString());

        ventaDto.setNomCliente(request.get("nomCliente").toString());
        ventaDto.setApellCliente(request.get("apellCliente").toString());
        ventaDto.setNomEmpleado(request.get("nomEmpleado").toString());
        ventaDto.setApellEmpleado(request.get("apellEmpleado").toString());
        ventaDto.setPrecio(Integer.parseInt(request.get("precio").toString()));

        String resp = this.ventaNegocio.guardarVenta(ventaDto);
        res.put("status", "ok");
        res.put("data", resp);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @GetMapping("/Eliminate/{id}")
    public  ResponseEntity<Map<String,Object>> eliminarVenta(@PathVariable int id){
        Map<String,Object> res=new HashMap<>();
        String resp=this.ventaNegocio.eliminar(id);
        res.put("status","ok");
        res.put("data",resp);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
