package com.stiven.Concesionario.app.controlador;

import com.stiven.Concesionario.app.dto.ClienteDto;
import com.stiven.Concesionario.app.entity.Cliente;
import com.stiven.Concesionario.app.negocio.ClienteNegocio;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.Objects.*;

@RestController
@RequestMapping(path = "/Cliente")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE,RequestMethod.HEAD})

public class ClienteController {

    @Autowired
    private ClienteNegocio clienteNegocio;

    @Autowired
    private ClienteDto clienteDto;

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> all() {



        List<ClienteDto> listaClientes = this.clienteNegocio.encontrarTodos();
        Map<String, Object> res = new HashMap<>();
        res.put("status", "ok");
        res.put("data", listaClientes);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/crear")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> crearCliente(@RequestBody Map<String, Object> request) {
        Map<String, Object> res = new HashMap<>();
        try{
            String telefonoStr = request.get("telefono").toString();

            if (!StringUtils.isNumeric(telefonoStr)) {
                // El valor del teléfono no es un número válido
                res.put("status", HttpStatus.BAD_REQUEST);
                res.put("code", "400");
                res.put("data", "El valor del teléfono no es válido.");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }
        System.out.println("@@@@@@" + request.toString());
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setId(0);
        clienteDto.setNombres(request.get("nombres").toString());
        clienteDto.setApellidos(request.get("apellidos").toString());
        clienteDto.setTelefono(Long.parseLong(telefonoStr));
        clienteDto.setCorreo(request.get("correo").toString());
        String resp = this.clienteNegocio.guardarCliente(clienteDto);
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
    public ResponseEntity<Map<String, Object>> actualizarCliente(@RequestBody Map<String, Object> request) {
        Map<String, Object> res = new HashMap<>();
        String telefonoStr = request.get("telefono").toString();

        if (!StringUtils.isNumeric(telefonoStr)) {
            // El valor del teléfono no es un número válido
            res.put("status", HttpStatus.BAD_REQUEST);
            res.put("code", "400");
            res.put("data", "El valor del teléfono no es válido.");
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
        clienteDto.setId(Integer.parseInt(request.get("id").toString()));
        clienteDto.setNombres(request.get("nombres").toString());
        clienteDto.setApellidos(request.get("apellidos").toString());
        clienteDto.setTelefono(Long.parseLong(telefonoStr));
        clienteDto.setCorreo(request.get("correo").toString());
        String resp = this.clienteNegocio.guardarCliente(clienteDto);
        res.put("status", "ok");
        res.put("data", resp);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @GetMapping("/Eliminate/{id}")
    public  ResponseEntity<Map<String,Object>> elimarCliente(@PathVariable int id){
        Map<String,Object> res=new HashMap<>();
        String resp=this.clienteNegocio.eliminar(id);
        res.put("status","ok");
        res.put("data",resp);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


}