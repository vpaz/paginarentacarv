
package inacap.webcomponet.pagina.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "modelos")

public class Modelo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private int idmodelo;
    private String nombremodelo;
    private String detalle;
    private Marca marca;
    
    
    public int getIdmodelo() {
        return idmodelo;
    }

    public void setIdmodelo(int idmodelo) {
        this.idmodelo = idmodelo;
    }

    public String getNombremodelo() {
        return nombremodelo;
    }

    public void setNombremodelo(String nombremodelo) {
        this.nombremodelo = nombremodelo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
    
    public Modelo() {
    }

    public Modelo(String nombremodelo, String detalle) {
        this.nombremodelo = nombremodelo;
        this.detalle = detalle;
    }

    private Modelo(int idmodelo, String nombremodelo, String detalle) {
        this.idmodelo = idmodelo;
        this.nombremodelo = nombremodelo;
        this.detalle = detalle;
        this.marca = marca;
    }
         
}
