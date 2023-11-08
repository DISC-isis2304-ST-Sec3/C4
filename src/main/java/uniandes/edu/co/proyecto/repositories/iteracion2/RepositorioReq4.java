package uniandes.edu.co.proyecto.repositories.iteracion2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.proyecto.entities.especiales.ServiciosEspeciales;

import java.util.List;

@Repository
public interface RepositorioReq4 extends JpaRepository<ServiciosEspeciales, Long> {
  @Query(
      value = "SELECT s.id, s.nombre, s.capacidad, s.tipo_cobro, s.cobro" +
          "FROM servicios s, reserva_servicios rs" +
          "WHERE s.cobro BETWEEN 50 AND 200" +
          "AND rs.horario BETWEEN TO_DATE('2022-11-01', 'YYYY-MM-DD') AND TO_DATE('2022-11-10', 'YYYY-MM-DD')" +
          "AND s.tipo_cobro = 'DIA';" ,
      nativeQuery = true)

  List<ServiciosEspeciales> ejecutarRequerimiento4(@Param("page") int page);
}



       
      