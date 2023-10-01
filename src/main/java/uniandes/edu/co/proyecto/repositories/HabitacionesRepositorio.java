package uniandes.edu.co.proyecto.repositories;


import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uniandes.edu.co.proyecto.entities.Habitacion;

import java.util.Collection;

public interface HabitacionesRepositorio extends JpaRepository<Habitacion, Long> {
  @Query(value = "SELECT * FROM HABITACIONES", nativeQuery = true)
  Collection<Habitacion> obtenerHabitaciones();

  @Query(value = "SELECT * FROM HABITACIONES WHERE IDHABITACION = :idhabitacion", nativeQuery = true)
  Habitacion obtenerHabitacion(@Param("idhabitacion") long idhabitacion);

  @Modifying
  @Transactional
  @Query(value = "INSERT INTO HABITACIONES (IDHABITACION, TIPO, CAPACIDAD, COSTO, IDHOTEL)" +
      "VALUES (hoteles_sequence.nextval, :tipo, :capacidad, :costo, :idhotel)", nativeQuery = true)
  void crearHabitacion(
      @Param("tipo") String tipo,
      @Param("capacidad") Integer capacidad,
      @Param("costo") Integer costo,
      @Param("idhotel") long idhotel
  );

  @Modifying
  @Transactional
  @Query(value = "UPDATE HABITACIONES " +
      "SET TIPO = :tipo, CAPACIDAD = :capacidad, COSTO = :costo, IDHOTEL = :idhotel WHERE IDHABITACION = :idhabitacion"
      , nativeQuery = true)
  void actualizarHabitacion(
      @Param("idhabitacion") long idhabitacion,
      @Param("tipo") String tipo,
      @Param("capacidad") Integer capacidad,
      @Param("costo") Integer costo,
      @Param("idhotel") long idhotel
  );

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM HABITACIONES WHERE IDHABITACION = :idhabitacion", nativeQuery = true)
  void eliminarHabitacion(@Param("idhabitacion") long idhabitacion);
}
