package fidelcorp.example.rutas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fidelcorp.example.rutas.entity.Chofer;
import java.util.List;

public interface ChoferRepository extends JpaRepository<Chofer, Integer> {

    List<Chofer> findAllByOrderByIdAsc();
    List<Chofer> findByCiudadAsignadaId(Integer ciudadId);

}
