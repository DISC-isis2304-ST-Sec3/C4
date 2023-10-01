package uniandes.edu.co.proyecto.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uniandes.edu.co.proyecto.entities.Salon;

import java.util.Collection;

public interface SalonesRepositorio extends JpaRepository<Salon, Long> {
  @Query(value = "SELECT * FROM SALONES", nativeQuery = true)
  Collection<Salon> obtenerSalones();

  @Query(value = "SELECT * FROM SALONES WHERE ID = :id", nativeQuery = true)
  Salon obtenerSalon(@Param("id") Long id);

  @Modifying
  @Transactional
  @Query(value = "INSERT INTO SALONES (ID, TIPO, COSTO, CAPACIDAD) VALUES (hoteles_sequence.nextval, :tipo, :costo, :capacidad)", nativeQuery = true)
  void crearSalon(@Param("tipo") String tipo, @Param("costo") int costo, @Param("capacidad") int capacidad);

  @Modifying
  @Transactional
  @Query(value = "UPDATE SALONES SET TIPO = :tipo, COSTO = :costo, CAPACIDAD = :capacidad WHERE ID = :id", nativeQuery = true)
  void actualizarSalon(@Param("id") long id, @Param("tipo") String tipo, @Param("costo") int costo, @Param("capacidad") int capacidad);

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM SALONES WHERE ID = :id", nativeQuery = true)
  void eliminarSalon(@Param("id") Long id);
}
