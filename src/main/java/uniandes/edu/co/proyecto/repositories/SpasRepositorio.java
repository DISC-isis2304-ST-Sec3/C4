package uniandes.edu.co.proyecto.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uniandes.edu.co.proyecto.entities.Piscina;
import uniandes.edu.co.proyecto.entities.Spa;

import java.util.Collection;
import java.util.Date;

public interface SpasRepositorio extends JpaRepository<Spa, Long> {
  @Query(value = "SELECT * FROM SPAS", nativeQuery = true)
  Collection<Spa> obtenerSpas();

  @Query(value = "SELECT * FROM SPAS WHERE ID_HOTEL = :idHotel", nativeQuery = true)
  Collection<Spa> obtenerSpasHotel(@Param("idHotel") long idHotel);

  @Query(value = "SELECT * FROM SPAS WHERE ID = :id", nativeQuery = true)
  Spa obtenerSpa(@Param("id") long id);

  @Modifying
  @Transactional
  @Query(value = "INSERT INTO SPAS (ID, COSTO, DESCRIPCION, ID_HOTEL)" +
      "VALUES (hoteles_sequence.nextval, :costo, :descripcion, :idHotel)", nativeQuery = true)
  void crearSpa(
      @Param("costo") int costo,
      @Param("descripcion") String descripcion,
      @Param("idHotel") long idHotel
  );

  @Modifying
  @Transactional
  @Query(value = "UPDATE SPAS " +
      "SET COSTO = :costo, DESCRIPCION = :descripcion, ID_HOTEL = :idHotel WHERE ID = :id"
      , nativeQuery = true)
  void actualizarSpa(
      @Param("id") long id,
      @Param("costo") int costo,
      @Param("descripcion") String descripcion,
      @Param("idHotel") long idHotel
  );

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM SPAS WHERE ID = :id", nativeQuery = true)
  void eliminarSpa(@Param("id") long id);
}
