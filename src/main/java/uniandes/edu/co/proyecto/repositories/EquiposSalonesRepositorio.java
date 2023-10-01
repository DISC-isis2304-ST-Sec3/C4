package uniandes.edu.co.proyecto.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uniandes.edu.co.proyecto.entities.EquiposSalones;

import java.util.Collection;

public interface EquiposSalonesRepositorio extends JpaRepository<EquiposSalones, Long> {
  @Query(value = "SELECT * FROM EQUIPOS_SALONES", nativeQuery = true)
  Collection<EquiposSalones> obtenerEquiposSalones();


  @Query(value = "SELECT * FROM EQUIPOS_SALONES WHERE ID_SALON = :idSalon", nativeQuery = true)
  Collection<EquiposSalones> obtenerEquiposSalon(@Param("idSalon") Long idSalon);


  @Query(value = "SELECT * FROM EQUIPOS_SALONES WHERE ID_EQUIPO = :idEquipo", nativeQuery = true)
  Collection<EquiposSalones> obtenerEquipoSalones(@Param("idEquipo") Long idEquipo);

  @Modifying
  @Transactional
  @Query(value = "INSERT INTO EQUIPOS_SALONES (ID_EQUIPO, ID_SALON) VALUES (:idEquipo, :idSalon)", nativeQuery = true)
  void relacionarEquipoConSalon(@Param("idEquipo") Long idEquipo, @Param("idSalon") Long idSalon);

  @Modifying
  @Transactional
  @Query(value = "UPDATE EQUIPOS_SALONES SET ID_EQUIPO = :idEquipoNuevo, ID_SALON = :idSalonNuevo " +
      "WHERE ID_EQUIPO = :idEquipo and ID_SALON = :idSalon", nativeQuery = true)
  void actualizarRelacionEquipoConSalon(
      @Param("idEquipo") Long idEquipo,
      @Param("idSalon") Long idSalon,
      @Param("idEquipoNuevo") Long idEquipoNuevo,
      @Param("idSalonNuevo") Long idSalonNuevo
  );

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM EQUIPOS_SALONES WHERE ID_EQUIPO = :idEquipo AND ID_SALON = :idSalon", nativeQuery = true)
  void eliminarRelacionEquipoConSalon(@Param("idEquipo") Long idEquipo, @Param("idSalon") Long idSalon);
}
