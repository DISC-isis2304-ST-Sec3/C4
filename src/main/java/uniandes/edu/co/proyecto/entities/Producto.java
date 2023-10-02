package uniandes.edu.co.proyecto.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "productos")
public class Producto {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long idProducto;
  private boolean todoIncluido;
  private String nombre;
  private int costo;
  private int limite;

  @ManyToOne
  @JoinColumn(name = "id_tienda", referencedColumnName = "id")
  private Tienda tienda;

  public Producto() {
  }

  public Producto(boolean todoIncluido, String nombre, int costo, int limite, Tienda tienda){
    this.todoIncluido = todoIncluido;
    this.nombre = nombre;
    this.costo = costo;
    this.limite = limite;
    this.tienda = tienda;
  }

  public Long getIdProducto() {
    return idProducto;
  }

  public void setIdProducto(Long idProducto) {
    this.idProducto = idProducto;
  }

  public boolean getTodoIncluido() {
  return todoIncluido;
  }

  public void setTodoIncluido(boolean todoIncluido) {
    this.todoIncluido = todoIncluido;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getCosto() {
    return costo;
  }

  public void setCosto(int costo) {
    this.costo = costo;
  }

  public int getLimite() {
    return limite;
  }

  public void setLimite(int limite) {
    this.limite = limite;
  }

  public Tienda getTienda() {
    return tienda;
  }

  public void setTienda(Tienda tienda) {
    this.tienda = tienda;
  }
}