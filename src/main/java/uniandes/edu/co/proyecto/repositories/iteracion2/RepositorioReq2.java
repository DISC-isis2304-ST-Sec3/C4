package uniandes.edu.co.proyecto.repositories.iteracion2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.proyecto.entities.especiales.ConsumosServicio;

import java.util.Date;
import java.util.List;

@Repository
public interface RepositorioReq2 extends JpaRepository<ConsumosServicio, Long> {
  @Query(
      value = "select servicios.id as servicio_id, servicios.nombre as nombre, count(consumos.id) as cantidad_consumos from servicios " +
          "inner join consumos on consumos.id_servicio = servicios.id " +
          "where consumos.fecha between :date1 and :date2 " +
          "group by servicios.id, servicios.nombre " +
          "order by cantidad_consumos desc " +
          "fetch first 20 rows only",
      nativeQuery = true
  )
  List<ConsumosServicio> ejecutarRequerimiento2(@Param("date1") Date date1, @Param("date2") Date date2);
}
