package uniandes.edu.co.proyecto.repositories.iteracion2;

import org.antlr.v4.runtime.atn.SemanticContext.AND;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.proyecto.entities.Usuario;

import java.util.Date;
import java.util.List;

@Repository
public interface RepositorioReq5 extends JpaRepository<Usuario, Long> {
    @Query(
        value = "select usuarios.id, usuarios.nombre, consumos.costo " + 
        "from usuarios join consumos on consumos.id_usuario = usuarios.id " + 
        "where consumos.fecha between :date1 and :date2 and usuarios.numDocumento = :numDocumento;",
        nativeQuery = true
    )
    List<Usuario> ejecutarRequerimiento5(@Param("numDocumento") String numDocumento, @Param("date1") Date date1, @Param("date2") Date date2);
  }
