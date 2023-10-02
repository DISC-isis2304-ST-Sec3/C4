package uniandes.edu.co.proyecto.entities;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "reservas")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idreserva;

  @Temporal(TemporalType.DATE)
  private Date inicio;

  @Temporal(TemporalType.DATE)
  private Date fin;
  
    @OneToOne
    @JoinColumn(name = "idhabitacion", referencedColumnName = "idhabitacion", nullable = false)
    private Habitacion habitacion;

     @OneToOne
    @JoinColumn(name = "idpago", referencedColumnName = "idpago", nullable = false)
    private Cuenta cuenta;
  
    private int cantidadpersonas;

    public Reserva() {
    }
  
    public Reserva(Date inicio, Date fin,int cantidadpersonas, Habitacion habitacion, Cuenta cuenta) {
      this.inicio = inicio;
      this.fin = fin;
      this.cantidadpersonas = cantidadpersonas;
      this.habitacion = habitacion;
    }
  
    public Long getId() {
      return idreserva;
    }
  
    public void setId(Long idreserva) {
      this.idreserva = idreserva;
    }
  
    public Date getInicio() {
      return inicio;
    }
  
    public void setInicio(Date inicio) {
      this.inicio = inicio;
    }
  
    public Date getFin() {
      return fin;
    }
  
    public void setFin(Date fin) {
      this.fin = fin;
    }
  
    public Integer getCantidadpersonas() {
      return cantidadpersonas;
    }
  
    public void setCosto(Integer cantidadpersonas) {
      this.cantidadpersonas = cantidadpersonas;
    }
  
    public Habitacion getHabitacion() {
      return habitacion;
    }
  
    public void setHabitacion(Habitacion habitacion) {
      this.habitacion = habitacion;
    }
}
