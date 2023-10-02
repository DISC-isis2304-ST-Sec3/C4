package uniandes.edu.co.proyecto.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "cuentas")
public class Cuenta {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long idpago;
  private String pagado;
  
public Cuenta() {
}

public Cuenta(String pagado) {
  this.pagado = pagado;
}

public Long getId() {
  return idpago;
}

public void setId(Long idpago) {
  this.idpago = idpago;
}

public String getPagado() {
  return pagado;
}

public void setPagado(String pagado) {
  this.pagado = pagado;
}

}
