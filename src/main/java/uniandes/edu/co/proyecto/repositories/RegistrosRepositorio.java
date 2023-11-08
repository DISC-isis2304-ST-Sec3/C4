package uniandes.edu.co.proyecto.repositories;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uniandes.edu.co.proyecto.entities.Registro;

import java.util.Collection;

public interface RegistrosRepositorio extends JpaRepository<Registro, Integer> {
  @Query(value = "SELECT * FROM REGISTROS", nativeQuery = true)
  Collection<Registro> obtenerRegistros();

  @Query(value = "SELECT * FROM REGISTROS WHERE DOCPERSONA = :docpersona", nativeQuery = true)
  Registro obtenerRegistro(@Param("docpersona") Integer docpersona);

  @Modifying
  @Transactional
  @Query(value = "INSERT INTO REGISTROS (DOCPERSONA, CAPACIDAD, IDRESERVA)" +
      "VALUES (:docpersona, :capacidad, :idreserva)", nativeQuery = true)
  void crearRegistro(
      @Param("docpersona") int docpersona,
      @Param("capacidad") Integer capacidad,
      @Param("idreserva") long idreserva
  );

  @Modifying
  @Transactional
  @Query(value = "UPDATE REGISTROS " +
      "SET DOCPERSONA = :docpersona, CAPACIDAD = :capacidad,  IDRESERVA = :idreserva WHERE DOCPERSONA = :docpersona"
      , nativeQuery = true)
  void actualizarRegistro(
      @Param("docpersona") Integer docpersona,
      @Param("capacidad") Integer capacidad,
      @Param("idreserva") long idreserva
  );

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM REGISTROS WHERE DOCPERSONA = :docpersona", nativeQuery = true)
  void eliminarRegistro(@Param("docpersona") Integer docpersona);
}
