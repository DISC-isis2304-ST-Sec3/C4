package uniandes.edu.co.proyecto.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uniandes.edu.co.proyecto.entities.ReservaServicio;

import java.util.Collection;
import java.util.Date;

public interface ReservasServicioRepositorio extends JpaRepository<ReservaServicio, Long> {
  @Query(value = "SELECT * FROM reservas_servicio", nativeQuery = true)
  Collection<ReservaServicio> obtenerReservasServicio();

  @Query(value = "SELECT * FROM reservas_servicio WHERE id = :id", nativeQuery = true)
  ReservaServicio obtenerReservaServicio(@Param("id") long id);

  @Modifying
  @Transactional
  @Query(value = "INSERT INTO reservas_servicio (horario, id_salon, id_spa) VALUES (:horario, :idSalon, :idSpa)", nativeQuery = true)
  void crearReservaServicio(
      @Param("horario") Date horario,
      @Param("idSalon") long idSalon,
      @Param("idSpa") long idSpa
  );

  @Modifying
  @Transactional
  @Query(value = "UPDATE reservas_servicio SET horario = :horario, id_salon = :idSalon, id_spa = :idSpa WHERE id = :id", nativeQuery = true)
  void actualizarReservaServicio(
      @Param("id") long id,
      @Param("horario") Date horario,
      @Param("idSalon") long idSalon,
      @Param("idSpa") long idSpa
  );

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM reservas_servicio WHERE id = :id", nativeQuery = true)
  void eliminarReservaServicio(@Param("id") long id);
}
