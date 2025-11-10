package fidelcorp.example.rutas.dto;

import java.util.List;

import fidelcorp.example.rutas.entity.Chofer;
import fidelcorp.example.rutas.entity.Ruta;
import lombok.Data;

@Data
public class RutaConChoferesDTO {
    private Ruta ruta;
    private List<Chofer> choferes;

    public RutaConChoferesDTO(Ruta ruta, List<Chofer> choferes) {
        this.ruta = ruta;
        this.choferes = choferes;
    }
}
