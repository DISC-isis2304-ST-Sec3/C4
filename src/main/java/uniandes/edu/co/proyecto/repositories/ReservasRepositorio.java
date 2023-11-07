package uniandes.edu.co.proyecto.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uniandes.edu.co.proyecto.entities.Reserva;

import java.util.Collection;
import java.util.Date;

public interface ReservasRepositorio extends JpaRepository<Reserva, Long> {
  @Query(value = "SELECT * FROM RESERVAS", nativeQuery = true)
  Collection<Reserva> obtenerReservas();

  @Query(value = "SELECT * FROM RESERVAS WHERE IDRESERVA = :idreserva", nativeQuery = true)
  Reserva obtenerReserva(@Param("idreserva") long idreserva);

  @Modifying
  @Transactional
  @Query(value = "INSERT INTO RESERVAS (IDRESERVA, FECHA_RESERVA, INICIO, FIN, CANTIDADPERSONAS, IDHABITACION, IDPAGO)" +
      "VALUES (hoteles_sequence.nextval, :fecha_reserva, :inicio, :fin, :cantidadpersonas, :idhabitacion, :idpago)", nativeQuery = true)
  void crearReserva(
      @Param("fecha_reserva") Date fechaReserva,
      @Param("inicio") Date inicio,
      @Param("fin") Date fin,
      @Param("cantidadpersonas") int cantidadpersonas,
      @Param("idhabitacion") long idhabitacion,
      @Param("idpago") long idpago
  );

  @Modifying
  @Transactional
  @Query(value = "UPDATE RESERVAS " +
      "SET INICIO = :inicio, FIN = :fin, CANTIDADPERSONAS = :cantidadpersonas, IDHABITACION = :idhabitacion, IDPAGO = :idpago WHERE IDRESERVA = :idreserva"
      , nativeQuery = true)
  void actualizarReserva(
      @Param("idreserva") long idreserva,
      @Param("inicio") Date inicio,
      @Param("fin") Date fin,
      @Param("cantidadpersonas") int cantidadpersonas,
      @Param("idhabitacion") long idhabitacion,
      @Param("idpago") long idpago
  );

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM RESERVAS WHERE IDRESERVA = :idreserva", nativeQuery = true)
  void eliminarReserva(@Param("idreserva") long idreserva);
}
