package uniandes.edu.co.proyecto.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class ReservaServicio {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Temporal(TemporalType.TIMESTAMP)
  private Date horario;

  @ManyToOne
  @JoinColumn(name = "id_salon", referencedColumnName = "id")
  private Salon salon;
}
