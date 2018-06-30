
package inacap.webcomponet.pagina.controler;

import inacap.webcomponet.pagina.model.Region;
import inacap.webcomponet.pagina.repository.RegionIR;
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
@RequestMapping("/region")
public class RegionController {
    

    @Autowired
   private RegionIR regionrepo;
    
    @GetMapping()
    public Iterable<Region> list() {
        return regionrepo.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Region> get(@PathVariable String id) {
        
          Optional<Region> rOptional = regionrepo.findById(Integer.parseInt(id));
        if(rOptional.isPresent()){
            Region regione = rOptional.get();
            return new ResponseEntity<>(regione, HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Region> put(@PathVariable String id, @RequestBody Region editarRegion) {
       
       Optional<Region> reOptional = regionrepo.findById(Integer.parseInt(id));
        if(reOptional.isPresent()){
            Region regione = reOptional.get();
            editarRegion.setIdRegion(regione.getIdRegion());
            regionrepo.save(editarRegion);
            return new ResponseEntity<>(editarRegion, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
        
        }
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody Region nuevaRegion) {
    nuevaRegion = regionrepo.save(nuevaRegion);
         Optional<Region> reOptional = regionrepo.findById(nuevaRegion.getIdRegion());
     if(reOptional.isPresent()){
         Region regione = reOptional.get();
      return new ResponseEntity<>(regione,HttpStatus.CREATED);
     }else{
     return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);     
     }
     
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Optional<Region> reOptional = regionrepo.findById(Integer.parseInt(id));
       
       if(reOptional.isPresent()){
           Region regione = reOptional.get();
           regionrepo.deleteById(regione.getIdRegion());
           return new ResponseEntity<>(regione,HttpStatus.OK);
       }else{
       
       return new ResponseEntity<>(null,HttpStatus.EXPECTATION_FAILED);
       }
       
    }
    
}


