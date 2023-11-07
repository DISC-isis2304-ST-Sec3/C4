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

  public Registro(int docpersona, int capacidad, Reserva reserva) {
    this.docpersona = docpersona;
    this.capacidad = capacidad;
    this.reserva = reserva;
  }

  public int getDocpersona() {
    return docpersona;
  }

  public void setDocpersona(int docpersona) {
    this.docpersona = docpersona;
  }

  public Reserva getReserva() {
    return reserva;
  }

  public void setReserva(Reserva reserva) {
    this.reserva = reserva;
  }

  public int getCapacidad() {
    return capacidad;
  }

  public void setCapacidad(int capacidad) {
    this.capacidad = capacidad;
  }
}