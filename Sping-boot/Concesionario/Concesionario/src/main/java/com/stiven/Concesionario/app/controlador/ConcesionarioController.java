package com.stiven.Concesionario.app.controlador;


import com.stiven.Concesionario.app.dto.ConcesionarioDto;
import com.stiven.Concesionario.app.entity.Concesionario;
import com.stiven.Concesionario.app.implementacion.ConcesionarioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.stiven.Concesionario.app.negocio.ConcesionarioNegocio;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(path = "/Concesionario")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE,RequestMethod.HEAD})

public class ConcesionarioController {
    @Autowired
    ConcesionarioImpl concesionarioimpl;

    @Autowired
    ConcesionarioNegocio concesionarioNegocio;

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<Map<String,Object>> encontrarTodos(){
        List<Concesionario> listaConcesionario= this.concesionarioimpl.encontrarTodos();

        Map<String,Object> res=new HashMap<>();
        res.put("status","ok");
        res.put("data",listaConcesionario);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/crear")
    @ResponseBody
    public ResponseEntity <Map<String,Object>> crearConcesionario(@RequestBody  Map<String,Object> request){
        Map<String,Object> res=new HashMap<>();
        try{
        System.out.println("@@@@@@"+request.toString());
        ConcesionarioDto concesionarioDto=new ConcesionarioDto();
        concesionarioDto.setId(0);
        concesionarioDto.setNombre(request.get("nombre").toString());
        concesionarioDto.setDireccion(request.get("direccion").toString());
        concesionarioDto.setTelefono(Integer.parseInt(request.get("telefono").toString()));
        String resp = this.concesionarioNegocio.guardarConcesionario(concesionarioDto);
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
    public ResponseEntity<Map<String, Object>> actualizarConcecionario(@RequestBody Map<String, Object> request) {
        Map<String, Object> res = new HashMap<>();
        ConcesionarioDto concesionarioDto=new ConcesionarioDto();
        concesionarioDto.setId(Integer.parseInt(request.get("id").toString()));
        concesionarioDto.setNombre(request.get("nombre").toString());
        concesionarioDto.setDireccion(request.get("direccion").toString());
        concesionarioDto.setTelefono(Integer.parseInt(request.get("telefono").toString()));
        String resp = this.concesionarioNegocio.guardarConcesionario(concesionarioDto);
        res.put("status", "ok");
        res.put("data", resp);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @GetMapping("/Eliminate/{id}")
    public  ResponseEntity<Map<String,Object>> elimarConcesionario(@PathVariable int id){
        Map<String,Object> res=new HashMap<>();
        String resp=this.concesionarioNegocio.eliminarConcesionario(id);
        res.put("status","ok");
        res.put("data",resp);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
