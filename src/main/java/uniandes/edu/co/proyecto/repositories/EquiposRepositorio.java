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

  @Query(value = "SELECT * FROM EQUIPOS WHERE ID IN (SELECT ID_EQUIPO FROM EQUIPOS_GIMNASIOS WHERE ID_GIMNASIO = :idGimnasio)", nativeQuery = true)
  Collection<Equipo> obtenerEquiposGimnasio(@Param("idGimnasio") Long idGimnasio);

  @Query(value = "SELECT * FROM EQUIPOS WHERE ID NOT IN (SELECT ID_EQUIPO FROM EQUIPOS_GIMNASIOS WHERE ID_GIMNASIO = :idGimnasio)", nativeQuery = true)
  Collection<Equipo> obtenerEquiposParaGimnasio(@Param("idGimnasio") Long idGimnasio);

  @Query(value = "SELECT * FROM EQUIPOS WHERE ID IN (SELECT ID_EQUIPO FROM EQUIPOS_SALONES WHERE ID_SALON = :idSalon)", nativeQuery = true)
  Collection<Equipo> obtenerEquiposSalon(@Param("idSalon") Long idSalon);

  @Query(value = "SELECT * FROM EQUIPOS WHERE ID NOT IN (SELECT ID_EQUIPO FROM EQUIPOS_SALONES WHERE ID_SALON = :idSalon)", nativeQuery = true)
  Collection<Equipo> obtenerEquiposParaSalon(@Param("idSalon") Long idSalon);

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
