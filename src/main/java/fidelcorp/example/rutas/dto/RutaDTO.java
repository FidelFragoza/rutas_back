package fidelcorp.example.rutas.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RutaDTO {


    @NotNull
    @Size(max = 15)
    private String nombreRuta;
    
    @NotNull
    private String tipoServicio;

    @NotNull
    private Integer capacidad;

    @NotNull
    private Integer ciudadId;

    @NotNull
    private Integer choferId;
}
