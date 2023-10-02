package uniandes.edu.co.proyecto.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import uniandes.edu.co.proyecto.entities.Usuario;

import java.util.Collection;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    @Query(value = "SELECT * FROM USUARIOS", nativeQuery = true)
  Collection<Usuario> obtenerUsuarios();

  @Query(value = "SELECT * FROM USUARIOS WHERE ID = :id", nativeQuery = true)
  Usuario obtenerUsuario(@Param("id") Long id);

  @Modifying
  @Transactional
  @Query(value = "INSERT INTO USUARIOS (ID, NOMBRE, NUMDOCUMENTO, NICKNAME, CONTRASEÑA) VALUES (usuarios_sequence.nextval, :nombre, :numdocumento, :nickname, contrasena)", nativeQuery = true)
  void crearUsuario(@Param("nombre") String tipo, @Param("numdocumento") int costo, @Param("nickname") String nickname, @Param("contrasena") String contrasena);

  @Modifying
  @Transactional
  @Query(value = "UPDATE USUARIOS SET NOMBRE = :nombre, NUMDOCUMENTO = :numdocumento, NICKNAME = :nickname, CONTRASEÑA = :contrasena WHERE ID = :id", nativeQuery = true)
  void actualizarUsuario(@Param("id") long id, @Param("nombre") String nombre, @Param("numdocumento") int numdocumento, @Param("nickname") String nickname, @Param("contrasena") String contrasena);

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM USUARIOS WHERE ID = :id", nativeQuery = true)
  void eliminarUsuario(@Param("id") Long id);
}
