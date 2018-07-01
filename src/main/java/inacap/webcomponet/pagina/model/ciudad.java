
package inacap.webcomponet.pagina.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ciudad")

public class ciudad {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private int idCiudad; 
    private String NombreCiudad;
    private String Detalle;
    private Region region;


    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getNombreCiudad() {
        return NombreCiudad;
    }

    public void setNombreCiudad(String NombreCiudad) {
        this.NombreCiudad = NombreCiudad;
    }

    public String getDetalle() {
        return Detalle;
    }

    public void setDetalle(String Detalle) {
        this.Detalle = Detalle;
    }

    public ciudad() {
    }

    public ciudad(String NombreCiudad, String Detalle) {
        this.NombreCiudad = NombreCiudad;
        this.Detalle = Detalle;
    }

    private ciudad(int idCiudad, String NombreCiudad, String Detalle) {
        this.idCiudad = idCiudad;
        this.NombreCiudad = NombreCiudad;
        this.Detalle = Detalle;
    }  
}