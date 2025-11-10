package fidelcorp.example.rutas.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Data;

@Data
public class ChoferDTO {
    @NotNull
    private Boolean activo;

    @NotNull
    @Size(max = 15)
    private String nombre;

    @NotNull
    @Size(max = 15)
    private String apellidoPaterno;

    @NotNull
    @Size(max = 15)
    private String apellidoMaterno;

    @NotNull
    private LocalDate fechaNacimiento;

    @NotNull
    private Double sueldo;

    @NotNull
    private Integer ciudadId;
}
