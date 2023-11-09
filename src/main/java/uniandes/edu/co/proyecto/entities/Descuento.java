package uniandes.edu.co.proyecto.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "descuentos")
public class Descuento {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long idDescuento;
  private int porcentaje;

  @ManyToOne
  @JoinColumn(name = "id_producto", referencedColumnName = "idProducto")
  private Producto producto;

  public Long getIdDescuento() {
    return idDescuento;
  }

  public void setIdDescuento(Long idDescuento) {
    this.idDescuento = idDescuento;
  }

  public int getPorcentaje() {
  return porcentaje;
  }

  public void setPorcentaje(int porcentaje) {
    this.porcentaje = porcentaje;
  }
 
  public Producto getProducto() {
  return producto;
  }

  public void setProducto(Producto producto) {
    this.producto = producto;
  }
}
