package fidelcorp.example.rutas.controller;

import fidelcorp.example.rutas.dto.RutaConChoferesDTO;
import fidelcorp.example.rutas.dto.RutaDTO;
import fidelcorp.example.rutas.entity.Chofer;
import fidelcorp.example.rutas.entity.Ruta;
import fidelcorp.example.rutas.repository.ChoferRepository;
import fidelcorp.example.rutas.service.RutaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rutas")
@RequiredArgsConstructor
public class RutaController {

    private final RutaService rutaService;
    private final ChoferRepository choferRepository;

    @GetMapping
    public ResponseEntity<List<Ruta>> listarTodos() {
        return ResponseEntity.ok(rutaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ruta> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(rutaService.obtenerPorId(id));
    }

    @GetMapping("/porCiudad/{ciudadId}")
    public ResponseEntity<List<Ruta>> obtenerRutasPorCiudad(@PathVariable Integer ciudadId) {
        List<Ruta> rutas = rutaService.findByCiudadIdConDetalles(ciudadId);
        return ResponseEntity.ok(rutas);
    }

    @GetMapping("/rutaCompleta/{id}")
    public ResponseEntity<RutaConChoferesDTO> obtenerRutaCompleta(@PathVariable Integer id) {
        Ruta ruta = rutaService.obtenerPorId(id);
        List<Chofer> choferes = choferRepository.findByCiudadAsignadaId(ruta.getCiudad().getId());
        RutaConChoferesDTO dto = new RutaConChoferesDTO(ruta, choferes);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/rutaChoferId/{id}")
    public ResponseEntity<Map<String, Object>> obtenerRutaChoferId(@PathVariable Integer id) {
        Ruta ruta = rutaService.obtenerPorId(id);
        List<Chofer> choferes = choferRepository.findByCiudadAsignadaId(ruta.getCiudad().getId());
        Integer choferIdAsignado = rutaService.obtenerIdChoferAsignado(id);

        Map<String, Object> response = new HashMap<>();
        response.put("ruta", ruta);
        response.put("choferes", choferes);
        response.put("choferIdAsignado", choferIdAsignado);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Ruta> crear(@Valid @RequestBody RutaDTO dto) {
        Ruta nuevaRuta = rutaService.guardar(dto);
        return new ResponseEntity<>(nuevaRuta, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ruta> actualizar(@PathVariable Integer id, @Valid @RequestBody RutaDTO dto) {
       Ruta rutaActualizada = rutaService.actualizar(id, dto);
        return ResponseEntity.ok(rutaActualizada);
    } 

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        rutaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
