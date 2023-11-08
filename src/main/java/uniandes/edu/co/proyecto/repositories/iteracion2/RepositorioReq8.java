package uniandes.edu.co.proyecto.repositories.iteracion2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.proyecto.entities.especiales.ServicioPocaDemanda;

import java.util.List;

@Repository
public interface RepositorioReq8 extends JpaRepository<ServicioPocaDemanda, Long> {
  @Query(
      value = "SELECT s.id, s.nombre, s.capacidad, s.tipo_cobro, s.cobro" +
          "FROM servicios s" +
          "WHERE s.id NOT IN (" +
          "SELECT rs.id_spa" +
          "FROM reserva_servicios rs" +
          "WHERE rs.horario >= ADD_MONTHS(SYSDATE, -12) " +
          "GROUP BY rs.id_spa" +
          "HAVING COUNT(DISTINCT TO_CHAR(rs.horario, 'IW')) >= 3);",
      nativeQuery = true)

  List<ServicioPocaDemanda> ejecutarRequerimiento8(@Param("page") int page);
}
