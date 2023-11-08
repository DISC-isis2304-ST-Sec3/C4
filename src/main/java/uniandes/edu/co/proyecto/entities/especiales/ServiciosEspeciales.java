package uniandes.edu.co.proyecto.entities.especiales;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ServiciosEspeciales {
  @Id
  @Column(name = "id")
  private int id;

  @Column(name = "nombre")
  private String nombre;

  @Column(name = "capacidad")
  private Integer capacidad;

  @Column(name = "tipo_cobro")
  private String tipo_cobro;

  @Column(name = "cobro")
  private Integer cobro;

  public ServiciosEspeciales() {}

  public ServiciosEspeciales(Integer id, String nombre, Integer capacidad,String tipo_cobro,Integer cobro) {
    this.id = id;
    this.nombre = nombre;
    this.capacidad = capacidad;
    this.tipo_cobro = tipo_cobro;
    this.cobro = cobro;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Integer getCapacidad() {
    return capacidad;
  }

  public void setCapacidad(Integer capacidad) {
    this.capacidad = capacidad;
  }

  public String getTipo_cobro() {
    return tipo_cobro;
  }

  public void setTipo_cobro(String tipo_cobro) {
    this.tipo_cobro = tipo_cobro;
  }

  public Integer getCobro() {
    return cobro;
  }

  public void setCobro(Integer cobro) {
    this.cobro = cobro;
  }
}