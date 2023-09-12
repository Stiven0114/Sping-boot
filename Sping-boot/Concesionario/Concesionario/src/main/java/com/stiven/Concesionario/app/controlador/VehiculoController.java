package com.stiven.Concesionario.app.controlador;


import com.stiven.Concesionario.app.dto.VehiculoDto;
import com.stiven.Concesionario.app.entity.Vehiculo;
import com.stiven.Concesionario.app.implementacion.VehiculoImpl;
import com.stiven.Concesionario.app.negocio.VehiculoNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/Vehiculo")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE,RequestMethod.HEAD})
public class VehiculoController {

    @Autowired
    VehiculoImpl vehiculoimpl;

    @Autowired
    VehiculoNegocio vehiculoNegocio;

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<Map<String,Object>> encontrarTodos(){
        List<Vehiculo> listaVehiculo= this.vehiculoimpl.encontrarTodos();
        System.out.println(listaVehiculo.toString());
        Map<String,Object> res=new HashMap<>();
        res.put("status","ok");
        res.put("data",listaVehiculo);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/crear")
    @ResponseBody
    public ResponseEntity <Map<String,Object>> crearVehiculo(@RequestBody  Map<String,Object> request){
        Map<String,Object> res=new HashMap<>();
        try{
            System.out.println("@@@@@@"+request.toString());
            VehiculoDto vehiculoDto=new VehiculoDto();
            vehiculoDto.setId(0);
            vehiculoDto.setTipoVehiculo(request.get("tipoVehiculo").toString());
            vehiculoDto.setModelo(Integer.parseInt(request.get("modelo").toString()));
            vehiculoDto.setCentCubicos(Integer.parseInt(request.get("centCubicos").toString()));
            vehiculoDto.setPlaca(request.get("placa").toString());
            vehiculoDto.setPrecio(Integer.parseInt(request.get("precio").toString()));
            vehiculoDto.setMarca(request.get("marca").toString());
            vehiculoDto.setColor(request.get("color").toString());
            String resp = this.vehiculoNegocio.guardarVehiculo(vehiculoDto);
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
    public ResponseEntity<Map<String, Object>> actualizarVehiculo(@RequestBody Map<String, Object> request) {
        Map<String, Object> res = new HashMap<>();
        VehiculoDto vehiculoDto=new VehiculoDto();
        vehiculoDto.setId(Integer.parseInt(request.get("id").toString()));
        vehiculoDto.setTipoVehiculo(request.get("tipoVehiculo").toString());
        vehiculoDto.setModelo(Integer.parseInt(request.get("modelo").toString()));
        vehiculoDto.setCentCubicos(Integer.parseInt(request.get("centCubicos").toString()));
        vehiculoDto.setPlaca(request.get("placa").toString());
        vehiculoDto.setPrecio(Integer.parseInt(request.get("precio").toString()));
        vehiculoDto.setMarca(request.get("marca").toString());
        vehiculoDto.setColor(request.get("color").toString());
        String resp = this.vehiculoNegocio.guardarVehiculo(vehiculoDto);
        res.put("status", "ok");
        res.put("data", resp);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @GetMapping("/Eliminate/{id}")
    public  ResponseEntity<Map<String,Object>> elimarVehiculo(@PathVariable int id){
        Map<String,Object> res=new HashMap<>();
        String resp=this.vehiculoNegocio.eliminar(id);
        res.put("status","ok");
        res.put("data",resp);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
