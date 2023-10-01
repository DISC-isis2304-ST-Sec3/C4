package uniandes.edu.co.proyecto.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uniandes.edu.co.proyecto.entities.Gimnasio;

import java.util.Collection;
import java.util.Date;

public interface GimnasiosRepositorio extends JpaRepository<Gimnasio, Long> {
  @Query(value = "SELECT * FROM GIMNASIOS", nativeQuery = true)
  Collection<Gimnasio> obtenerGimnasios();

  @Query(value = "SELECT * FROM GIMNASIOS WHERE ID = :id", nativeQuery = true)
  Gimnasio obtenerGimnasio(@Param("id") long id);

  @Modifying
  @Transactional
  @Query(value = "INSERT INTO GIMNASIOS (ID, HORARIO_ABRE, HORARIO_CIERRA, ID_SERVICIO)" +
      "VALUES (hoteles_sequence.nextval, :horarioAbre, :horarioCierra, :idServicio)", nativeQuery = true)
  void crearGimnasio(
      @Param("horarioAbre") Date horarioAbre,
      @Param("horarioCierra") Date horarioCierra,
      @Param("idServicio") long idServicio
  );

  @Modifying
  @Transactional
  @Query(value = "UPDATE GIMNASIOS " +
      "SET HORARIO_ABRE = :horarioAbre, HORARIO_CIERRA = :horarioCierra, ID_SERVICIO = :idServicio WHERE ID = :id"
      , nativeQuery = true)
  void actualizarGimnasio(
      @Param("id") long id,
      @Param("horarioAbre") Date horarioAbre,
      @Param("horarioCierra") Date horarioCierra,
      @Param("idServicio") long idServicio
  );

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM GIMNASIOS WHERE ID = :id", nativeQuery = true)
  void eliminarGimnasio(@Param("id") long id);
}
