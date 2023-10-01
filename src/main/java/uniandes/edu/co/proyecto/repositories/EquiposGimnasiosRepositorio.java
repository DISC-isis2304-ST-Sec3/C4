package uniandes.edu.co.proyecto.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uniandes.edu.co.proyecto.entities.EquiposGimnasios;

import java.util.Collection;

public interface EquiposGimnasiosRepositorio extends JpaRepository<EquiposGimnasios, Long> {
  @Query(value = "SELECT * FROM EQUIPOS_GIMNASIOS", nativeQuery = true)
  Collection<EquiposGimnasios> obtenerEquiposGimnasios();


  @Query(value = "SELECT * FROM EQUIPOS_GIMNASIOS WHERE ID_EQUIPO = :idEquipo", nativeQuery = true)
  Collection<EquiposGimnasios> obtenerEquipoGimnasios(@Param("idEquipo") Long idEquipo);

  @Modifying
  @Transactional
  @Query(value = "INSERT INTO EQUIPOS_GIMNASIOS (ID_EQUIPO, ID_GIMNASIO) VALUES (:idEquipo, :idGimnasio)", nativeQuery = true)
  void relacionarEquipoConGimnasio(@Param("idEquipo") Long idEquipo, @Param("idGimnasio") Long idGimnasio);

  @Modifying
  @Transactional
  @Query(value = "UPDATE EQUIPOS_GIMNASIOS SET ID_EQUIPO = :idEquipoNuevo, ID_GIMNASIO = :idGimnasioNuevo " +
      "WHERE ID_EQUIPO = :idEquipo and ID_GIMNASIO = :idGimnasio", nativeQuery = true)
  void actualizarRelacionEquipoConGimnasio(
      @Param("idEquipo") Long idEquipo,
      @Param("idGimnasio") Long idGimnasio,
      @Param("idEquipoNuevo") Long idEquipoNuevo,
      @Param("idGimnasioNuevo") Long idGimnasioNuevo
  );

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM EQUIPOS_GIMNASIOS WHERE ID_EQUIPO = :idEquipo AND ID_GIMNASIO = :idGimnasio", nativeQuery = true)
  void eliminarRelacionEquipoConGimnasio(@Param("idEquipo") Long idEquipo, @Param("idGimnasio") Long idGimnasio);
}
