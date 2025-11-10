package fidelcorp.example.rutas.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ciudades")
@Getter
@Setter 
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Ciudad {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "nombre", nullable = false, unique = true, length = 100)
    private String nombre;
}
