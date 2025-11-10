package fidelcorp.example.rutas.service;

import fidelcorp.example.rutas.dto.ChoferDTO;
import fidelcorp.example.rutas.entity.Chofer;

import java.util.List;

public interface ChoferService {
    Chofer guardar(ChoferDTO dto);
    List<Chofer> listarTodos();
    Chofer obtenerPorId(Integer id);
    void eliminarPorId(Integer id);
    Chofer actualizar(Integer id, ChoferDTO chofer);
    List<Chofer> listarPorCiudad(Integer ciudadId);

}
