package uniandes.edu.co.proyecto.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "piscinas")
public class Piscina {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private int profundidad;

  @Temporal(TemporalType.TIME)
  private Date horarioAbre;

  @Temporal(TemporalType.TIME)
  private Date horarioCierra;

  @OneToOne
  @JoinColumn(name = "id_servicio", referencedColumnName = "id", nullable = false)
  private Servicio servicio;

  public Piscina() {
  }

  public Piscina(int profundidad, Date horarioAbre, Date horarioCierra, Servicio servicio) {
    this.profundidad = profundidad;
    this.horarioAbre = horarioAbre;
    this.horarioCierra = horarioCierra;
    this.servicio = servicio;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getProfundidad() {
    return profundidad;
  }

  public void setProfundidad(int profundidad) {
    this.profundidad = profundidad;
  }

  public Date getHorarioAbre() {
    return horarioAbre;
  }

  public void setHorarioAbre(Date horarioAbre) {
    this.horarioAbre = horarioAbre;
  }

  public Date getHorarioCierra() {
    return horarioCierra;
  }

  public void setHorarioCierra(Date horarioCierra) {
    this.horarioCierra = horarioCierra;
  }

  public Servicio getServicio() {
    return servicio;
  }

  public void setServicio(Servicio servicio) {
    this.servicio = servicio;
  }
}
