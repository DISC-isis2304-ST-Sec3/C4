package uniandes.edu.co.proyecto.repositories.iteracion2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.proyecto.entities.especiales.FechasImportantes;

import java.util.Date;
import java.util.List;

@Repository
public interface RepositorioReq6 extends JpaRepository<FechasImportantes, Long> {
  @Query(
      value = "(SELECT 'Mayor Ocupación' AS tipo, fecha_reserva AS fecha, COUNT(*) AS valor " +
          "FROM reservas " +
          "GROUP BY fecha_reserva " +
          "ORDER BY COUNT(*) DESC " +
          "FETCH FIRST 1 ROW ONLY) " +
          "UNION (SELECT 'Mayores Ingresos' AS tipo, fecha, SUM(costo) AS valor " +
          "FROM consumos " +
          "GROUP BY fecha "+
          "ORDER BY SUM(costo) DESC "+
          "FETCH FIRST 1 ROW ONLY)"+
          "UNION "+
          "(SELECT 'Menor Demanda' AS tipo, fecha_reserva AS fecha, COUNT(*) AS valor " +
          "FROM reservas " +
          "GROUP BY fecha_reserva " +
          "ORDER BY COUNT(*) ASC " + 
          "FETCH FIRST 1 ROW ONLY)",
      nativeQuery = true)

  List<FechasImportantes> ejecutarRequerimiento6(@Param("page") int page);
}
