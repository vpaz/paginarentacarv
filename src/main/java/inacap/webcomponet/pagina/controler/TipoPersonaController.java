
package inacap.webcomponet.pagina.controler;



import inacap.webcomponet.pagina.model.TipoPersona;
import inacap.webcomponet.pagina.repository.TipoPersonaIR;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

  @RestController
@RequestMapping("/tipopersona")

  
public class TipoPersonaController {
    
    @Autowired
   private TipoPersonaIR tprepo;
    
    @GetMapping()
    public Iterable<TipoPersona> list() {
        return tprepo.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TipoPersona> get(@PathVariable String id) {
        
          Optional<TipoPersona> rOptional = tprepo.findById(Integer.parseInt(id));
        if(rOptional.isPresent()){
            TipoPersona tpe = rOptional.get();
            return new ResponseEntity<>(tpe, HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TipoPersona> put(@PathVariable String id, @RequestBody TipoPersona editartp) {
       
       Optional<TipoPersona> reOptional = tprepo.findById(Integer.parseInt(id));
        if(reOptional.isPresent()){
            TipoPersona tpe = reOptional.get();
            editartp.setIdPersona(tpe.getIdPersona());
            tprepo.save(editartp);
            return new ResponseEntity<>(editartp, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
        
        }
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody TipoPersona nuevoTipoPersona) {
    nuevoTipoPersona = tprepo.save(nuevoTipoPersona);
         Optional<TipoPersona> reOptional = tprepo.findById(nuevoTipoPersona.getIdPersona());
     if(reOptional.isPresent()){
         TipoPersona tpe = reOptional.get();
      return new ResponseEntity<>(tpe,HttpStatus.CREATED);
     }else{
     return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);     
     }
     
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Optional<TipoPersona> reOptional = tprepo.findById(Integer.parseInt(id));
       
       if(reOptional.isPresent()){
           TipoPersona tpe = reOptional.get();
           tprepo.deleteById(tpe.getIdPersona());
           return new ResponseEntity<>(tpe,HttpStatus.OK);
       }else{
       
       return new ResponseEntity<>(null,HttpStatus.EXPECTATION_FAILED);
       }
       
    }
    
}
