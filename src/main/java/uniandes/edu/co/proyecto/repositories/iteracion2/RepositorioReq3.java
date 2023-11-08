package uniandes.edu.co.proyecto.repositories.iteracion2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.proyecto.entities.especiales.OcupacionHabitacion;

import java.util.List;

@Repository
public interface RepositorioReq3 extends JpaRepository<OcupacionHabitacion, Long> {
  @Query(
      value = "select " +
          "habitaciones.idhabitacion, " +
          "ROUND((sum(reservas.cantidadpersonas) / sum(habitaciones.capacidad)) * 100, 2) as ocupacion, " +
          "count(reservas.idreserva) as cantidad_reservas from habitaciones " +
            "inner join reservas on reservas.idhabitacion = habitaciones.idhabitacion " +
          "where idhotel = :idhotel and reservas.inicio >= ADD_MONTHS(CURRENT_DATE, -12) " +
          "group by habitaciones.idhabitacion " +
          "order by ocupacion desc",
      nativeQuery = true
  )
  List<OcupacionHabitacion> ejecutarRequerimiento3(@Param("idhotel") long idhotel);
}
