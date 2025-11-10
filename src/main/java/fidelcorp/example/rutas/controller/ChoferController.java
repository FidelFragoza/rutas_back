package fidelcorp.example.rutas.controller;

import fidelcorp.example.rutas.dto.ChoferDTO;
import fidelcorp.example.rutas.dto.RutaConChoferesDTO;
import fidelcorp.example.rutas.entity.Chofer;
import fidelcorp.example.rutas.entity.Ruta;
import fidelcorp.example.rutas.repository.ChoferRepository;
import fidelcorp.example.rutas.service.ChoferService;
import fidelcorp.example.rutas.service.RutaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/choferes")
@RequiredArgsConstructor
public class ChoferController {

    private final ChoferService choferService;
    private final ChoferRepository choferRepository;
    private final RutaService rutaService;

    @GetMapping
    public ResponseEntity<List<Chofer>> listarTodos() {
        return ResponseEntity.ok(choferService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chofer> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(choferService.obtenerPorId(id));
    }

    @GetMapping("/filtrarPorCiudad/{ciudadId}")
    public ResponseEntity<List<Chofer>> listarPorCiudad(@PathVariable Integer ciudadId) {
        List<Chofer> choferes = choferService.listarPorCiudad(ciudadId);
        return ResponseEntity.ok(choferes);
    }

    @GetMapping("/rutaCompleta/{id}")
    public ResponseEntity<RutaConChoferesDTO> obtenerRutaCompleta(@PathVariable Integer id) {
        Ruta ruta = rutaService.obtenerPorId(id);
        List<Chofer> choferes = choferRepository.findByCiudadAsignadaId(ruta.getCiudad().getId());
        RutaConChoferesDTO dto = new RutaConChoferesDTO(ruta, choferes);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Chofer> crear(@Valid @RequestBody ChoferDTO dto) {
        Chofer nuevoChofer = choferService.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoChofer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Chofer> actualizar(@PathVariable Integer id, @Valid @RequestBody ChoferDTO dto) {
        Chofer actualizado = choferService.actualizar(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarChofer(@PathVariable Integer id) {
        try {
            choferService.eliminarPorId(id);
            return ResponseEntity.ok().build();
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Error interno: " + ex.getMessage()));
        }
    }

}
