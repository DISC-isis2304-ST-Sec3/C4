package uniandes.edu.co.proyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.proyecto.entities.especiales.DineroHabitacion;

import java.util.List;

@Repository
public interface EspecialesRepositorio extends JpaRepository<DineroHabitacion, Long> {
  @Query(value = "select sum(consumos.costo) as coste_total, habitaciones.idhabitacion from consumos inner join reservas on reservas.idreserva = consumos.id_reserva inner join habitaciones on habitaciones.idhabitacion = reservas.idhabitacion where consumos.fecha >= add_months(CURRENT_DATE, -12) group by habitaciones.idhabitacion order by coste_total", nativeQuery = true)
  List<DineroHabitacion> ejecutarRequerimiento1();
}
