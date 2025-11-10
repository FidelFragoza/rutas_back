package fidelcorp.example.rutas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fidelcorp.example.rutas.entity.Chofer;
import fidelcorp.example.rutas.entity.Ruta;

@Repository
public interface RutaRepository extends JpaRepository<Ruta, Integer> {
    @Query("SELECT r FROM Ruta r JOIN FETCH r.ciudad ci JOIN FETCH r.chofer ch WHERE ci.id = :ciudadId")
    List<Ruta> findByCiudadIdConDetalles(@Param("ciudadId") Integer ciudadId);

    List<Chofer> findByCiudadId(Integer ciudadId);

}
