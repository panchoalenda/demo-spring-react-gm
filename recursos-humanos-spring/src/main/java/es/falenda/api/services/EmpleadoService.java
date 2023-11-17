package es.falenda.api.services;

import es.falenda.api.models.Empleado;
import es.falenda.api.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService implements IEmpleadoService{
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Override
    public List<Empleado> listar() {
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado mostrarEmpleadoPoId(Long id) {
       Empleado empBD = empleadoRepository.findById(id).orElse(null);
        return empBD;
    }

    @Override
    public Empleado guardar(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @Override
    public void eliminar(Empleado empleado) {
                empleadoRepository.delete(empleado);
    }
}
