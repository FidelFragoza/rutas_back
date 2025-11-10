package fidelcorp.example.rutas.service.impl;

import fidelcorp.example.rutas.dto.ChoferDTO;
import fidelcorp.example.rutas.entity.Chofer;
import fidelcorp.example.rutas.entity.Ciudad;
import fidelcorp.example.rutas.repository.ChoferRepository;
import fidelcorp.example.rutas.repository.CiudadRepository;
import fidelcorp.example.rutas.service.ChoferService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChoferServiceImpl implements ChoferService {

    private final ChoferRepository choferRepository;
    private final CiudadRepository ciudadRepository;

    @Override
    public List<Chofer> listarTodos() {
        return choferRepository.findAllByOrderByIdAsc();
    }

    @Override
    public List<Chofer> listarPorCiudad(Integer ciudadId) {
        return choferRepository.findByCiudadAsignadaId(ciudadId);
    }

    @Override
    public Chofer guardar(ChoferDTO dto) {
        validarChofer(dto);

        Ciudad ciudad = ciudadRepository.findById(dto.getCiudadId())
                .orElseThrow(() -> new EntityNotFoundException("Ciudad no encontrada"));

        Chofer chofer = new Chofer();
        chofer.setActivo(dto.getActivo());
        chofer.setNombre(dto.getNombre());
        chofer.setApellidoPaterno(dto.getApellidoPaterno());
        chofer.setApellidoMaterno(dto.getApellidoMaterno());
        chofer.setFechaNacimiento(dto.getFechaNacimiento());
        chofer.setSueldo(dto.getSueldo());
        chofer.setCiudadAsignada(ciudad);

        return choferRepository.save(chofer);
    }

    @Override
    public Chofer obtenerPorId(Integer id) {
        Objects.requireNonNull(id, "El id no puede ser null");
        return choferRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Chofer no encontrado"));
    }

    private void validarChofer(ChoferDTO dto) {
        LocalDate hoy = LocalDate.now();
        LocalDate nacimiento = dto.getFechaNacimiento();
        if (nacimiento == null || Period.between(nacimiento, hoy).getYears() < 18) {
            throw new IllegalArgumentException("El chofer debe ser mayor de 18 años");
        }
        if (dto.getSueldo() == null || dto.getSueldo() < 0) {
            throw new IllegalArgumentException("El sueldo debe ser un número positivo");
        }
    }

    @Override
    public Chofer actualizar(Integer id, ChoferDTO dto) {
        validarChofer(dto);

        Chofer chofer = choferRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Chofer no encontrado"));
        Ciudad ciudad = ciudadRepository.findById(dto.getCiudadId())
                .orElseThrow(() -> new EntityNotFoundException("Ciudad no encontrada"));

        chofer.setNombre(dto.getNombre());
        chofer.setApellidoPaterno(dto.getApellidoPaterno());
        chofer.setApellidoMaterno(dto.getApellidoMaterno());
        chofer.setFechaNacimiento(dto.getFechaNacimiento());
        chofer.setSueldo(dto.getSueldo());
        chofer.setActivo(dto.getActivo());
        chofer.setCiudadAsignada(ciudad);

        return choferRepository.save(chofer);
    }

    @Override
    public void eliminarPorId(Integer id) {
        Chofer chofer = choferRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Chofer no encontrado"));

        if (chofer.getRutas() != null && !chofer.getRutas().isEmpty()) {
            String rutas = chofer.getRutas().stream()
                    .map(r -> r.getNombreRuta())
                    .collect(Collectors.joining(", "));
            throw new IllegalStateException("No se puede eliminar el chofer porque tiene rutas asignadas: " + rutas);
        }

        choferRepository.deleteById(id);
    }

}
