
package inacap.webcomponet.pagina.controler;

import inacap.webcomponet.pagina.model.Traccion;
import inacap.webcomponet.pagina.repository.TraccionIR;
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
@RequestMapping("/traccion")
public class TraccionController {
    

    
    @Autowired
    private TraccionIR traccionrepo;
    
    @GetMapping()
    public Iterable<Traccion> list() {
        return traccionrepo.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Traccion> get(@PathVariable String id) {
        Optional<Traccion> tOptional = traccionrepo.findById(Integer.parseInt(id));
        if(tOptional.isPresent()){
                Traccion traccione = tOptional.get();
                return new ResponseEntity<>(traccione, HttpStatus.FOUND);
        }else{
                 return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Traccion> put(@PathVariable String id, @RequestBody Traccion editarTraccion) {
        Optional<Traccion> tOptional = traccionrepo.findById(Integer.parseInt(id));
        if(tOptional.isPresent()){
            Traccion traccione = tOptional.get();
            editarTraccion.setIdTraccion(traccione.getIdTraccion());
            traccionrepo.save(editarTraccion);
            return new ResponseEntity<>(editarTraccion, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
        
        }
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody Traccion nuevaTraccion) {
      nuevaTraccion = traccionrepo.save(nuevaTraccion);
         Optional<Traccion> tOptional = traccionrepo.findById(nuevaTraccion.getIdTraccion());
     if(tOptional.isPresent()){
         Traccion traccione = tOptional.get();
      return new ResponseEntity<>(traccione,HttpStatus.CREATED);
     }else{
     return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);     
     }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
      Optional<Traccion> tOptional = traccionrepo.findById(Integer.parseInt(id));
       
       if(tOptional.isPresent()){
           Traccion traccione = tOptional.get();
           traccionrepo.deleteById(traccione.getIdTraccion());
           return new ResponseEntity<>(traccione,HttpStatus.OK);
       }else{
       
       return new ResponseEntity<>(null,HttpStatus.EXPECTATION_FAILED);
       }
    }
    
}


