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
  @Query(value = "SELECT * FROM reserva_servicios", nativeQuery = true)
  Collection<ReservaServicio> obtenerReservasServicio();

  @Query(value = "SELECT * FROM reserva_servicios WHERE id = :id", nativeQuery = true)
  ReservaServicio obtenerReservaServicio(@Param("id") long id);

  @Modifying
  @Transactional
  @Query(value = "INSERT INTO reserva_servicios (id, horario, duracion, id_salon, id_spa) VALUES (hoteles_sequence.nextval, :horario, :duracion, :idSalon, :idSpa)", nativeQuery = true)
  void crearReservaServicio(
      @Param("horario") Date horario,
      @Param("duracion") int duracion,
      @Param("idSalon") long idSalon,
      @Param("idSpa") long idSpa
  );

  @Modifying
  @Transactional
  @Query(value = "INSERT INTO reserva_servicios (id, horario, duracion, id_salon) VALUES (hoteles_sequence.nextval, :horario, :duracion, :idSalon)", nativeQuery = true)
  void crearReservaSalon(
      @Param("horario") Date horario,
      @Param("duracion") int duracion,
      @Param("idSalon") long idSalon
  );

  @Modifying
  @Transactional
  @Query(value = "INSERT INTO reserva_servicios (id, horario, duracion, id_spa) VALUES (hoteles_sequence.nextval, :horario, :duracion, :idSpa)", nativeQuery = true)
  void crearReservaSpa(
      @Param("horario") Date horario,
      @Param("duracion") int duracion,
      @Param("idSpa") long idSpa
  );

  @Modifying
  @Transactional
  @Query(value = "UPDATE reserva_servicios SET horario = :horario, duracion = :duracion, id_salon = :idSalon, id_spa = :idSpa WHERE id = :id", nativeQuery = true)
  void actualizarReservaServicio(
      @Param("id") long id,
      @Param("horario") Date horario,
      @Param("duracion") int duracion,
      @Param("idSalon") long idSalon,
      @Param("idSpa") long idSpa
  );

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM reserva_servicios WHERE id = :id", nativeQuery = true)
  void eliminarReservaServicio(@Param("id") long id);
}
