package fidelcorp.example.rutas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fidelcorp.example.rutas.entity.Ciudad;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Integer> {
    
}
