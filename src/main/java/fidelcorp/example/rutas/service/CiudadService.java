package fidelcorp.example.rutas.service;

import fidelcorp.example.rutas.entity.Ciudad;
import java.util.List;

public interface CiudadService {
    List<Ciudad> listarTodos();
    Ciudad guardar(Ciudad ciudad);
    Ciudad obtenerPorId(Integer id);
    Ciudad actualizar(Integer id, Ciudad ciudad);
    void eliminarPorId(Integer id);
}
