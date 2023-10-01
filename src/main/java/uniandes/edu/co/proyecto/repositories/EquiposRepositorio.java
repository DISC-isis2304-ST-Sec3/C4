package uniandes.edu.co.proyecto.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uniandes.edu.co.proyecto.entities.Equipo;

import java.util.Collection;

public interface EquiposRepositorio extends JpaRepository<Equipo, Long> {

  @Query(value = "SELECT * FROM EQUIPOS", nativeQuery = true)
  Collection<Equipo> obtenerEquipos();

  @Query(value = "SELECT * FROM EQUIPOS WHERE ID = :id", nativeQuery = true)
  Equipo obtenerEquipo(@Param("id") Long id);

  @Modifying
  @Transactional
  @Query(value = "INSERT INTO EQUIPOS (ID, TIPO_EQUIPO, COSTO_ADICIONAL)" +
      "VALUES (hoteles_sequence.nextval, :tipoEquipo, :costoAdicional)"
      , nativeQuery = true)
  void crearEquipo(@Param("tipoEquipo") String tipoEquipo, @Param("costoAdicional") int costoAdicional);

  @Modifying
  @Transactional
  @Query(value =
      "UPDATE EQUIPOS SET TIPO_EQUIPO = :tipoEquipo, COSTO_ADICIONAL = :costoAdicional WHERE ID = :id",
      nativeQuery = true)
  void actualizarEquipo(@Param("id") Long id, @Param("tipoEquipo") String tipoEquipo, @Param("costoAdicional") int costoAdicional);

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM EQUIPOS WHERE ID = :id", nativeQuery = true)
  void eliminarEquipo(@Param("id") Long id);
}
