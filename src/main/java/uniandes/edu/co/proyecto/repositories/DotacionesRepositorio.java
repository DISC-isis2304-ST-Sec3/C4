package uniandes.edu.co.proyecto.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uniandes.edu.co.proyecto.entities.Dotacion;

import java.util.Collection;

public interface DotacionesRepositorio extends JpaRepository<Dotacion, Long> {
  @Query(value = "SELECT * FROM DOTACIONES", nativeQuery = true)
  Collection<Dotacion> obtenerDotaciones();

  @Query(value = "SELECT * FROM DOTACIONES WHERE IDDOTACION = :iddotacion", nativeQuery = true)
  Dotacion obtenerDotacion(@Param("iddotacion") Long iddotacion);

  @Modifying
  @Transactional
  @Query(value = "INSERT INTO DOTACIONES (IDDOTACION, NOMBRE, CANTIDAD, COSTOADICIONAL) VALUES (hoteles_sequence.nextval, :nombre, :cantidad, :costoAdicional)", nativeQuery = true)
  void crearDotacion(@Param("nombre") String nombre, @Param("cantidad") int cantidad, @Param("costoAdicional") int costoAdicional);

  @Modifying
  @Transactional
  @Query(value = "UPDATE DOTACIONES SET NOMBRE = :nombre, CANTIDAD = :cantidad, COSTOADICIONAL = :costoAdicional WHERE IDDOTACION = :iddotacion", nativeQuery = true)
  void actualizarDotacion(@Param("iddotacion") long iddotacion, @Param("nombre") String nombre, @Param("cantidad") int cantidad, @Param("costoAdicional") int costoAdicional);

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM DOTACIONES WHERE IDDOTACION = :iddotacion", nativeQuery = true)
  void eliminarDotacion(@Param("iddotacion") Long iddotacion);
}
