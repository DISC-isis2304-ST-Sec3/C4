package uniandes.edu.co.proyecto.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uniandes.edu.co.proyecto.entities.Piscina;

import java.util.Collection;
import java.util.Date;

public interface PiscinasRepositorio extends JpaRepository<Piscina, Long> {
  @Query(value = "SELECT * FROM PISCINAS", nativeQuery = true)
  Collection<Piscina> obtenerPiscinas();

  @Modifying
  @Transactional
  @Query(value = "INSERT INTO PISCINAS (ID, PROFUNDIDAD, HORARIO_ABRE, HORARIO_CIERRA, ID_SERVICIO)" +
      "VALUES (hoteles_sequence.nextval, :profundidad, :horarioAbre, :horarioCierra, :idServicio)", nativeQuery = true)
  void crearPiscina(
      @Param("profundidad") int profundidad,
      @Param("horarioAbre") Date horarioAbre,
      @Param("horarioCierra") Date horarioCierra,
      @Param("idServicio") long idServicio
    );
}
