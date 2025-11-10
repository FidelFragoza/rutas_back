package fidelcorp.example.rutas.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "choferes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Chofer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Boolean activo;

    @Column(length = 15, nullable = false)
    private String nombre;

    @Column(name = "apellido_paterno", length = 15, nullable = false)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", length = 15, nullable = false)
    private String apellidoMaterno;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(nullable = false)
    private Double sueldo;

    @ManyToOne
    @JoinColumn(name = "ciudad_asignada_id", nullable = false)
    private Ciudad ciudadAsignada;

    @OneToMany(mappedBy = "chofer")
    @JsonManagedReference
    private List<Ruta> rutas;

}
