package uniandes.edu.co.proyecto.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String nombre;
  private int numDocumento;
  private String nickname;
  private String contrasena;

  public Usuario() {
  }

  public Usuario(String nombre, int numDocumento, String nickname, String contrasena) {
    this.nombre = nombre;
    this.numDocumento = numDocumento;
    this.nickname = nickname;
    this.contrasena = contrasena;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getNumDocumento() {
    return numDocumento;
  }

  public void setNumDocumento(int numDocumento) {
    this.numDocumento = numDocumento;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getContrasena() {
    return contrasena;
  }

  public void setContrasena(String contrasena) {
    this.contrasena = contrasena;
  }
}
