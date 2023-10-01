package uniandes.edu.co.proyecto.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uniandes.edu.co.proyecto.entities.Hoteles;

import java.util.Collection;

public interface HotelesRepositorio extends JpaRepository<Hoteles, Long> {
  @Query(value = "SELECT * FROM HOTELES", nativeQuery = true)
  Collection<Hoteles> obtenerHoteles();

  @Query(value = "SELECT * FROM HOTELES WHERE IDHOTEL = :idhotel", nativeQuery = true)
  Hoteles obtenerHoteles(@Param("idhotel") Long idhotel);

  @Modifying
  @Transactional
  @Query(value = "INSERT INTO HOTELES (IDHOTELES, NOMBRE, ESTRELLAS, PAIS) VALUES (hoteles_sequence.nextval, :nombre, :estrellas, :pais)", nativeQuery = true)
  void crearHotel(@Param("nombre") String nombre, @Param("estrellas") int estrellas, @Param("pais") String pais);

  @Modifying
  @Transactional
  @Query(value = "UPDATE HOTELES SET NOMBRE = :nombre, ESTRELLAS = :estrellas, PAIS = :pais WHERE IDHOTEL = :idhotel", nativeQuery = true)
  void actualizarHotel(@Param("idhotel") long idhotel, @Param("nombre") String nombre, @Param("estrellas") int estrellas, @Param("pais") String pais);

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM HOTELES WHERE IDHOTEL = :idhotel", nativeQuery = true)
  void eliminarHotel(@Param("idhotel") Long idhotel);
}
