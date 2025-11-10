package fidelcorp.example.rutas.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import fidelcorp.example.rutas.entity.Ciudad;
import fidelcorp.example.rutas.repository.CiudadRepository;
import fidelcorp.example.rutas.service.CiudadService;
import io.micrometer.common.lang.NonNull;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CiudadServiceImpl implements CiudadService {

    private final CiudadRepository ciudadRepository;

    @Override
    public List<Ciudad> listarTodos() {
        return ciudadRepository.findAll();
    }

    @Override
    public Ciudad guardar(Ciudad ciudad) {
        Objects.requireNonNull(ciudad, "La ciudad no puede ser null");
        return ciudadRepository.save(ciudad);
    }

    @Override
    public Ciudad actualizar(Integer id, Ciudad ciudad) {
        Ciudad ciudadExistente = obtenerPorId(id);
        ciudadExistente.setNombre(ciudad.getNombre());
        // actualiza más campos si es necesario
        return ciudadRepository.save(ciudadExistente);
    }

    @Override
    public Ciudad obtenerPorId(@NonNull Integer id) {
        Objects.requireNonNull(id, "El id no puede ser null");
        return ciudadRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ciudad no encontrada"));
    }

    @Override
    public void eliminarPorId(@NonNull Integer id) {
        Objects.requireNonNull(id, "El id no puede ser null");
        ciudadRepository.deleteById(id);
    }

}
