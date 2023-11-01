package uniandes.edu.co.proyecto.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "reservas_servicio")
public class ReservaServicio {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Temporal(TemporalType.TIMESTAMP)
  private Date horario;

  private int duracion;

  @ManyToOne
  @JoinColumn(name = "id_salon", referencedColumnName = "id")
  private Salon salon;

  @ManyToOne
  @JoinColumn(name = "id_spa", referencedColumnName = "id")
  private Spa spa;

  public ReservaServicio() {
  }

  public ReservaServicio(Date horario, int duracion, Salon salon, Spa spa) {
    this.horario = horario;
    this.duracion = duracion;
    this.salon = salon;
    this.spa = spa;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getHorario() {
    return horario;
  }

  public void setHorario(Date horario) {
    this.horario = horario;
  }

  public int getDuracion() {
    return duracion;
  }

  public void setDuracion(int duracion) {
    this.duracion = duracion;
  }

  public Salon getSalon() {
    return salon;
  }

  public void setSalon(Salon salon) {
    this.salon = salon;
  }

  public Spa getSpa() {
    return spa;
  }

  public void setSpa(Spa spa) {
    this.spa = spa;
  }
}
