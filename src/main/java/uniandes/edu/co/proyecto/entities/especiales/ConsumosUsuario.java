package uniandes.edu.co.proyecto.entities.especiales;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ConsumosUsuario {
    @Id
    @Column(name="id")
    private Long id;
          
    private int cantConsumos; 
    
    public ConsumosUsuario() {
    }

    public ConsumosUsuario(Long id, int cantConsumos) {
        this.id = id;
        this.cantConsumos = cantConsumos;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }
        
    public void setcantConsumos(int cantConsumos) {
        this.cantConsumos = cantConsumos;
    }

    public int getcantConsumos() {
        return this.cantConsumos;
    }

}
