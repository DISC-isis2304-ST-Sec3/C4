package uniandes.edu.co.proyecto.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uniandes.edu.co.proyecto.entities.Servicio;

import java.util.Collection;

public interface ServiciosRepositorio extends JpaRepository<Servicio, Long> {
  @Query(value = "SELECT * FROM servicios", nativeQuery = true)
  Collection<Servicio> obtenerServicios();

  @Query(value = "SELECT * FROM servicios WHERE id = :id", nativeQuery = true)
  Servicio obtenerServicio(@Param("id") Long id);

  @Modifying
  @Transactional
  @Query(value = "INSERT INTO servicios (id, nombre, capacidad, tipo_cobro, cobro) VALUES (hoteles_sequence.nextval, :nombre, :capacidad, :tipoCobro, :cobro)", nativeQuery = true)
  void crearServicio(@Param("nombre") String nombre, @Param("capacidad") int capacidad, @Param("tipoCobro") String tipoCobro, @Param("cobro") int cobro);

  @Modifying
  @Transactional
  @Query(value = "UPDATE servicios SET nombre = :nombre, capacidad = :capacidad, tipo_cobro = :tipoCobro, cobro = :cobro WHERE id = :id", nativeQuery = true)
  void actualizarServicio(@Param("id") Long id, @Param("nombre") String nombre, @Param("capacidad") int capacidad, @Param("tipoCobro") String tipoCobro, @Param("cobro") int cobro);

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM servicios WHERE id = :id", nativeQuery = true)
  void eliminarServicio(@Param("id") Long id);
}
