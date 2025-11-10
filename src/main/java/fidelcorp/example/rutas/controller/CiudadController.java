package fidelcorp.example.rutas.controller;

import fidelcorp.example.rutas.entity.Ciudad;
import fidelcorp.example.rutas.service.CiudadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ciudades")
@RequiredArgsConstructor
public class CiudadController {

    private final CiudadService ciudadService;

    @GetMapping
    public ResponseEntity<List<Ciudad>> listarTodos() {
        List<Ciudad> ciudades = ciudadService.listarTodos();
        return ResponseEntity.ok(ciudades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ciudad> obtenerPorId(@PathVariable Integer id) {
        Ciudad ciudad = ciudadService.obtenerPorId(id);
        return ResponseEntity.ok(ciudad);
    }

    @PostMapping
    public ResponseEntity<Ciudad> crear(@RequestBody Ciudad ciudad) {
        Ciudad nuevaCiudad = ciudadService.guardar(ciudad);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCiudad);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ciudad> actualizar(@PathVariable Integer id, @RequestBody Ciudad ciudad) {
        Ciudad ciudadActualizada = ciudadService.actualizar(id, ciudad);
        return ResponseEntity.ok(ciudadActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        ciudadService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
