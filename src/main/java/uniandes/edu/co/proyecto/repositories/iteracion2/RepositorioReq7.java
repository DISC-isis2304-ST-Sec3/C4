package uniandes.edu.co.proyecto.repositories.iteracion2;

import org.antlr.v4.runtime.atn.SemanticContext.AND;
import org.antlr.v4.runtime.atn.SemanticContext.OR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.proyecto.entities.Usuario;

import java.util.Date;
import java.util.List;

@Repository
public interface RepositorioReq7 extends JpaRepository<Usuario, Long> {
    @Query(
        value = "SELECT " +
                "u.nombre AS nombre_cliente, " +
                "r.inicio AS fecha_inicio_estadia, " +
                "r.fin AS fecha_fin_estadia, " +
                "SUM(d.costoadicional) AS consumo_total " +
                "FROM usuario u " +
                "JOIN registros rg ON u.numdocumento = rg.docpersona " +
                "JOIN reservas r ON rg.idreserva = r.idreserva " +
                "JOIN habitaciones h ON r.idhabitacion = h.idhabitacion " +
                "JOIN hdotaciones hd ON h.idhabitacion = hd.idhabitacion " +
                "JOIN dotaciones d ON hd.iddotacion = d.iddotacion " +
                "WHERE r.inicio >= add_months(CURRENT_DATE, -12) " +
                "GROUP BY u.nombre, r.inicio, r.fin " +
                "HAVING (SUM(EXTRACT(DAY FROM r.fin - r.inicio)) >= 14 OR SUM(d.costoadicional) > 15000000) " +
                "ORDER BY nombre_cliente, fecha_inicio_estadia;",
        nativeQuery = true
    )
    List<Usuario> ejecutarRequerimiento5();
  }
