package fidelcorp.example.rutas.service;

import fidelcorp.example.rutas.dto.RutaDTO;
import fidelcorp.example.rutas.entity.Ruta;

import java.util.List;

public interface RutaService {
    Ruta guardar(RutaDTO dto);
    List<Ruta> listarTodos();
    Ruta obtenerPorId(Integer id);
    Ruta actualizar(Integer id, RutaDTO dto);
    void eliminar(Integer id);
    List<Ruta> findByCiudadIdConDetalles(Integer ciudadId);
    Integer obtenerIdChoferAsignado(Integer rutaId);
}
