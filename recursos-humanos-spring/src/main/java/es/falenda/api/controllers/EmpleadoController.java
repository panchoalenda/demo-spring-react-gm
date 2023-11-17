package es.falenda.api.controllers;

import es.falenda.api.exception.RecursoNoEncontradoException;
import es.falenda.api.models.Empleado;
import es.falenda.api.services.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("rh-app/empleados")
@CrossOrigin(value = "http://localhost:3000")
public class EmpleadoController {
    @Autowired
    private IEmpleadoService empleadoService;
    private Logger           logger;

    @GetMapping
    public List<Empleado> listar() {
        List<Empleado> emple = empleadoService.listar();
        emple.forEach(System.out::println);
        return emple;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> listarPorId(@PathVariable Long id) {
        Empleado empleado = empleadoService.mostrarEmpleadoPoId(id);

        if (empleado == null) {
            throw new RecursoNoEncontradoException("No se encontró el empleado id: " + id);
        }
        return ResponseEntity.ok(empleado);
    }

    @PostMapping
    public Empleado agregar(@RequestBody Empleado empleado) {
        System.out.println("Empleado a agregar: " + empleado);
        return empleadoService.guardar(empleado);
    }
    @PutMapping("/{id}")
    public Empleado actualizar(@PathVariable Long id, @RequestBody Empleado empleado) {
    Empleado empleadoABuscar = empleadoService.mostrarEmpleadoPoId(id);
       if(empleadoABuscar == null){
           throw new RecursoNoEncontradoException("No se encontró el empleado id: " + id);
       }
        System.out.println("Empleado actualizado: " + empleado);
        return empleadoService.guardar(empleado);
    }

 /*   @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Empleado> eliminar(@PathVariable Long id) {
        Empleado empleado = empleadoService.mostrarEmpleadoPoId(id);
        if (empleado == null) {
            throw new RecursoNoEncontradoException("No se encontró el empleado id: " + id);
        }
        empleadoService.eliminar(empleado);
        return ResponseEntity.status(HttpStatus.OK).build();
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminar(@PathVariable Long id){
        Empleado empleado = empleadoService.mostrarEmpleadoPoId(id);
        if (empleado == null){
            throw new RecursoNoEncontradoException("En id no existe en la base de datos");
        }
        empleadoService.eliminar(empleado);

        //La respuesta es un Json {"eliminado":"true"}
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
