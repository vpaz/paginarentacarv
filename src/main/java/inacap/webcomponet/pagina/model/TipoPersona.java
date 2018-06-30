
package inacap.webcomponet.pagina.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipopersona")
public class TipoPersona {
    
    
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int idPersona;
   private String nombreTipoPersona, detalle;

    public TipoPersona() {
    }

    public TipoPersona(String nombreTipoPersona, String detalle) {
        this.nombreTipoPersona = nombreTipoPersona;
        this.detalle = detalle;
    }

    public TipoPersona(int idPersona, String nombreTipoPersona, String detalle) {
        this.idPersona = idPersona;
        this.nombreTipoPersona = nombreTipoPersona;
        this.detalle = detalle;
    }
   
   
   
    
}
