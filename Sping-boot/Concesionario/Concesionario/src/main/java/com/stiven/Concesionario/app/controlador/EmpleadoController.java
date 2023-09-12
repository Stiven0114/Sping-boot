package com.stiven.Concesionario.app.controlador;

import com.stiven.Concesionario.app.dto.EmpleadoDto;
import com.stiven.Concesionario.app.entity.Empleado;
import com.stiven.Concesionario.app.implementacion.EmpleadoImpl;

import com.stiven.Concesionario.app.negocio.EmpleadoNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/Empleado")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE,RequestMethod.HEAD})
public class EmpleadoController {
    @Autowired
    EmpleadoImpl empleadoimpl;

    @Autowired
    EmpleadoNegocio empleadoNegocio;

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<Map<String,Object>> encontrarTodos(){
        List<Empleado> listaEmpleado= this.empleadoimpl.encontrarTodos();
        System.out.println(listaEmpleado.toString());
        Map<String,Object> res=new HashMap<>();
        res.put("status","ok");
        res.put("data",listaEmpleado);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/crear")
    @ResponseBody
    public ResponseEntity <Map<String,Object>> crearEmpleado(@RequestBody  Map<String,Object> request){
        Map<String,Object> res=new HashMap<>();
        try{
            System.out.println("@@@@@@"+request.toString());
            EmpleadoDto empleadoDto=new EmpleadoDto();
            empleadoDto.setId(0);
            empleadoDto.setNombres(request.get("nombres").toString());
            empleadoDto.setApellidos(request.get("apellidos").toString());
            empleadoDto.setTelefono(Integer.parseInt(request.get("telefono").toString()));
            empleadoDto.setCorreo(request.get("correo").toString());
            String resp = this.empleadoNegocio.guardarEmpleado(empleadoDto);
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
    public ResponseEntity<Map<String, Object>> actualizarEmpleado(@RequestBody Map<String, Object> request) {
        Map<String, Object> res = new HashMap<>();
        EmpleadoDto empleadoDto=new EmpleadoDto();
        empleadoDto.setId(Integer.parseInt(request.get("id").toString()));
        empleadoDto.setNombres(request.get("nombres").toString());
        empleadoDto.setApellidos(request.get("apellidos").toString());
        empleadoDto.setTelefono(Integer.parseInt(request.get("telefono").toString()));
        empleadoDto.setCorreo(request.get("correo").toString());
        String resp = this.empleadoNegocio.guardarEmpleado(empleadoDto);
        res.put("status", "ok");
        res.put("data", resp);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @GetMapping("/Eliminate/{id}")
    public  ResponseEntity<Map<String,Object>> elimarEmpleado(@PathVariable int id){
        Map<String,Object> res=new HashMap<>();
        String resp=this.empleadoNegocio.eliminarEmpleado(id);
        res.put("status","ok");
        res.put("data",resp);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
