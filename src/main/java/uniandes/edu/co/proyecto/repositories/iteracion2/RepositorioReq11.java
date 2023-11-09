package uniandes.edu.co.proyecto.repositories.iteracion2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.proyecto.entities.especiales.ConsumosServicio;
import uniandes.edu.co.proyecto.entities.especiales.Funcionamiento;

import java.util.List;

@Repository
public interface RepositorioReq11 extends JpaRepository<Funcionamiento, Integer> {
  @Query(
      value = "select " +
          "c.SEMANA, " +
          "servicio_menos_consumo, " +
          "menor_cantidad, " +
          "servicio_mas_consumo, " +
          "mayor_cantidad, " +
          "habitacion_menos_pedida, " +
          "menor_cantidad_reservas, " +
          "habitacion_mas_pedida, " +
          "mayor_cantidad_reservas " +
        "from (" +
          "select " +
            "semana, " +
            "max(id_servicio) keep ( dense_rank first order by consumos_servicio) as servicio_menos_consumo, " +
            "min(consumos_servicio) as menor_cantidad, " +
            "max(id_servicio) keep ( dense_rank last order by consumos_servicio) as servicio_mas_consumo, " +
            "max(consumos_servicio) as mayor_cantidad " +
          "from (" +
            "select " +
              "TO_CHAR(consumos.fecha, 'WW') as SEMANA, " +
              "consumos.id_servicio, " +
              "count(consumos.id_servicio) as consumos_servicio " +
            "from consumos " +
            "where consumos.id_servicio IS NOT NULL and EXTRACT(year from consumos.fecha) = EXTRACT(year from CURRENT_DATE) " +
            "group by TO_CHAR(consumos.fecha, 'WW'), consumos.id_servicio" +
          ") " +
          "group by semana " +
        ") c full join (" +
          "select " +
            "semana, " +
            "max(idhabitacion) keep ( dense_rank first order by reservas_habitacion) as habitacion_menos_pedida, " +
            "min(reservas_habitacion) as menor_cantidad_reservas, " +
            "max(idhabitacion) keep ( dense_rank last order by reservas_habitacion) as habitacion_mas_pedida, " +
            "max(reservas_habitacion) as mayor_cantidad_reservas " +
          "from ( " +
            "select " +
              "TO_CHAR(reservas.fecha_reserva, 'WW') as SEMANA, " +
              "reservas.idhabitacion, " +
              "count(reservas.idhabitacion) as reservas_habitacion " +
            "from reservas " +
            "where reservas.idhabitacion IS NOT NULL and EXTRACT(year from reservas.fecha_reserva) = EXTRACT(year from CURRENT_DATE) " +
            "group by TO_CHAR(reservas.fecha_reserva, 'WW'), reservas.idhabitacion " +
          ") " +
          "group by semana " +
        ") h on c.SEMANA = h.SEMANA",
      nativeQuery = true
  )
  List<Funcionamiento> ejecutarRequerimiento11();
}
