package uniandes.edu.co.proyecto.repositories;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uniandes.edu.co.proyecto.entities.Cuenta;


import java.util.Collection;

public interface CuentasRepositorio extends JpaRepository<Cuenta, Long> {
  @Query(value = "SELECT * FROM CUENTAS", nativeQuery = true)
  Collection<Cuenta> obtenerCuentas();

  @Query(value = "SELECT * FROM CUENTAS WHERE IDPAGO = :idpago", nativeQuery = true)
  Cuenta obtenerCuenta(@Param("idpago") Long idpago);

  @Modifying
  @Transactional
  @Query(value = "INSERT INTO CUENTAS (IDPAGO, PAGO) VALUES (hoteles_sequence.nextval, :pago)", nativeQuery = true)
  void crearCuenta(@Param("pago") String pago );

  @Modifying
  @Transactional
  @Query(value = "UPDATE CUENTAS SET PAGO = :pago", nativeQuery = true)
  void actualizarCuenta(@Param("idpago") long idpago, @Param("pago") String pago );

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM CUENTAS WHERE IDPAGO = :idpago", nativeQuery = true)
  void eliminarCuenta(@Param("idpago") Long idpago);
}
