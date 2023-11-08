package uniandes.edu.co.proyecto.entities.especiales;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ConsumoUsuarioFechas {
    @Id
    @Column(name="id")
    private Long id;

    private String nombre;

    private int costo;

    public ConsumoUsuarioFechas() {
    }

    public ConsumoUsuarioFechas(Long id, String nombre, int costo) {
        this.id = id;
        this.nombre = nombre;
        this.costo = costo;
    }

    public void setId(Long id) {
            this.id = id;
    }

    public Long getId() {
        return this.id;
    }
        
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }
        
    public void setCosto(int costo) {
        this.costo = costo;
    }

    public int getCosto() {
        return this.costo;
    }
}
