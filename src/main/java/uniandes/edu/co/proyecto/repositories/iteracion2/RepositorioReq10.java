package uniandes.edu.co.proyecto.repositories.iteracion2;

import org.antlr.v4.runtime.atn.SemanticContext.AND;
import org.antlr.v4.runtime.atn.SemanticContext.OR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.proyecto.entities.Usuario;
import uniandes.edu.co.proyecto.entities.especiales.ConsumosUsuario;

import java.util.Date;
import java.util.List;

@Repository
public interface RepositorioReq10 extends JpaRepository<ConsumosUsuario, Long> {
  @Query(
      value = "select usuarios.id, count(consumos.id_servicio) as cantConsumos from usuarios " +
      "inner join consumos on consumos.id_usuario = usuarios.id " +
      "where consumos.fecha between :date1 and :date2 and consumos.id_servicio = :servicio " +
      "having count(consumos.id_servicio)=0 " +
      "group by usuarios.id;",
      nativeQuery = true
  )
  List<ConsumosUsuario> ejecutarRequerimiento10(@Param("servicio") String servicio, @Param("date1") Date date1, @Param("date2") Date date2);
}