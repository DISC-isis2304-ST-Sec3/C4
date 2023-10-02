package uniandes.edu.co.proyecto.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "registros")
public class Registro {
  
  @Id
  private int docpersona;

  @OneToOne
  @JoinColumn(name = "idreserva", referencedColumnName = "idreserva", nullable = false)
  private Reserva reserva;

  private int capacidad;
 
  public Registro() {
  }

  public Registro( int docpersona, int capacidad, Reserva reserva) {
    this.docpersona = docpersona;
    this.capacidad = capacidad;
    this.reserva = reserva;
  }

  public int getId() {
    return docpersona;
  }

  public void setId(int docpersona) {
    this.docpersona = docpersona;
  }


  public Integer getCapacidad() {
    return capacidad;
  }

  public void setCapacidad(Integer capacidad) {
    this.capacidad = capacidad;
  }

  public Reserva getReserva() {
    return reserva;
  }

  public void setReserva(Reserva reserva) {
    this.reserva = reserva;
  }


}