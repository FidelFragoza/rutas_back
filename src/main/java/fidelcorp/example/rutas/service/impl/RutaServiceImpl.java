package fidelcorp.example.rutas.service.impl;

import fidelcorp.example.rutas.dto.RutaDTO;
import fidelcorp.example.rutas.entity.Ruta;
import fidelcorp.example.rutas.entity.Ciudad;
import fidelcorp.example.rutas.entity.Chofer;
import fidelcorp.example.rutas.entity.enums.TipoServicio;
import fidelcorp.example.rutas.repository.CiudadRepository;
import fidelcorp.example.rutas.repository.ChoferRepository;
import fidelcorp.example.rutas.repository.RutaRepository;
import fidelcorp.example.rutas.service.RutaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RutaServiceImpl implements RutaService {

    private final RutaRepository rutaRepository;
    private final CiudadRepository ciudadRepository;
    private final ChoferRepository choferRepository;

    @Override
    public Ruta guardar(RutaDTO dto) {
        Ciudad ciudad = ciudadRepository.findById(dto.getCiudadId())
                .orElseThrow(() -> new EntityNotFoundException("Ciudad no encontrada"));
        Chofer chofer = choferRepository.findById(dto.getChoferId())
                .orElseThrow(() -> new EntityNotFoundException("Chofer no encontrado"));

        validarCapacidad(dto.getTipoServicio(), dto.getCapacidad());

        Ruta ruta = new Ruta();
        ruta.setNombreRuta(dto.getNombreRuta());
        ruta.setTipoServicio(TipoServicio.valueOf(dto.getTipoServicio()));
        ruta.setCapacidad(dto.getCapacidad());
        ruta.setCiudad(ciudad);
        ruta.setChofer(chofer);

        return rutaRepository.save(ruta);
    }

    @Override
    public List<Ruta> listarTodos() {
        return rutaRepository.findAll();
    }

    @Override
    public Ruta obtenerPorId(Integer id) {
        return rutaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ruta no encontrada"));
    }

    @Override
    public List<Ruta> findByCiudadIdConDetalles(Integer ciudadId) {
        return rutaRepository.findByCiudadIdConDetalles(ciudadId);
    }

    @Override
    public Ruta actualizar(Integer id, RutaDTO dto) {
        Ruta ruta = rutaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ruta no encontrada"));

        // No actualizar nombreRuta ni ciudad para respetar campos deshabilitados
        // Asegúrate que el dto contenga choferId, tipoServicio y capacidad para
        // modificar

        Chofer chofer = choferRepository.findById(dto.getChoferId())
                .orElseThrow(() -> new EntityNotFoundException("Chofer no encontrado"));

        // mantener ciudad actual sin cambio
        // ruta.setCiudad(ruta.getCiudad());

        validarCapacidad(dto.getTipoServicio(), dto.getCapacidad());

        ruta.setChofer(chofer);
        ruta.setTipoServicio(TipoServicio.valueOf(dto.getTipoServicio()));
        ruta.setCapacidad(dto.getCapacidad());

        return rutaRepository.save(ruta);
    }

    @Override
    public void eliminar(Integer id) {
        rutaRepository.deleteById(id);
    }

    private void validarCapacidad(String tipoServicioStr, Integer capacidad) {
        TipoServicio tipoServicio = TipoServicio.valueOf(tipoServicioStr);
        if (tipoServicio == TipoServicio.Personal) {
            if (capacidad <= 0 || capacidad > 34) {
                throw new IllegalArgumentException("Capacidad inválida para tipo Personal");
            }
        } else if (tipoServicio == TipoServicio.Articulos) {
            if (capacidad <= 0 || capacidad > 100) {
                throw new IllegalArgumentException("Capacidad inválida para tipo Artículos");
            }
        }
    }

    @Override
    public Integer obtenerIdChoferAsignado(Integer rutaId) {
        Ruta ruta = rutaRepository.findById(rutaId)
                .orElseThrow(() -> new EntityNotFoundException("Ruta no encontrada"));
        return ruta.getChofer() != null ? ruta.getChofer().getId() : null;
    }

}
