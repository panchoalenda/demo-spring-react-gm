package es.falenda.api.services;

import es.falenda.api.models.Empleado;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IEmpleadoService {
    @Transactional(readOnly = true)
    List<Empleado> listar();

    @Transactional(readOnly = true)
    Empleado mostrarEmpleadoPoId(Long id);

    @Transactional
    Empleado guardar(Empleado empleado);

    @Transactional
    void eliminar(Empleado empleado);
}
