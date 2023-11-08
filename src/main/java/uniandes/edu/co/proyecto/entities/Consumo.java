package uniandes.edu.co.proyecto.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "consumos")
public class Consumo {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Temporal(TemporalType.TIMESTAMP)
  private Date fecha;

  private int costo;

  @ManyToOne
  @JoinColumn(name = "id_reserva", referencedColumnName = "idreserva")
  private Reserva reserva;

  @ManyToOne
  @JoinColumn(name = "id_reserva_servicio", referencedColumnName = "id")
  private ReservaServicio salon;

  @ManyToOne
  @JoinColumn(name = "id_servicio", referencedColumnName = "id")
  private Servicio servicio;

  @ManyToOne
  @JoinColumn(name = "id_producto", referencedColumnName = "id")
  private Producto spa;

  @ManyToOne
  @JoinColumn(name = "id_usuario", referencedColumnName = "id")
  private Usuario usuario ;


  public Consumo() {
  }

  public Consumo(Date fecha, int costo, Reserva reserva, ReservaServicio salon, Servicio servicio, Usuario usuario) {
    this.fecha = fecha;
    this.costo = costo;
    this.reserva = reserva;
    this.salon = salon;
    this.servicio = servicio;
    this.usuario = usuario;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Date getFecha() {
    return fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

  public int getCosto() {
    return costo;
  }

  public void setCosto(int costo) {
    this.costo = costo;
  }

  public Reserva getReserva() {
    return reserva;
  }

  public void setReserva(Reserva reserva) {
    this.reserva = reserva;
  }

  public ReservaServicio getSalon() {
    return salon;
  }

  public void setSalon(ReservaServicio salon) {
    this.salon = salon;
  }

  public Servicio getServicio() {
    return servicio;
  }

  public void setServicio(Servicio servicio) {
    this.servicio = servicio;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }
}
