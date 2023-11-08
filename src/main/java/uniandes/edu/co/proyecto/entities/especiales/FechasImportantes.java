package uniandes.edu.co.proyecto.entities.especiales;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class FechasImportantes {

  @Column(name = "tipo")
  private String tipo;

  @Column(name = "fecha")
  private Date fecha;

  @Column(name = "valor")
  private Integer valor;

  public FechasImportantes() {}

  public FechasImportantes(String tipo, Date fecha, Integer valor) {
    this.tipo = tipo;
    this.fecha = fecha;
    this.valor = valor;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public Date getFecha() {
    return fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

  public Integer getValor() {
    return valor;
  }

  public void setValor(Integer valor) {
    this.valor = valor;
  }
}