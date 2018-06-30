/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inacap.webcomponet.pagina.controler;

import inacap.webcomponet.pagina.model.Transmision;
import inacap.webcomponet.pagina.repository.TransmisionIR;
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

/**
 *
 * @author Vale
 */
@RestController
@RequestMapping("/transmision")
public class TransmisionController {
    

    
    @Autowired
    private TransmisionIR transmisionrepo;
    
    @GetMapping()
    public Iterable<Transmision> list() {
        
        return transmisionrepo.findAll();
                
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Transmision> get(@PathVariable String id) {
          Optional<Transmision> tOptional = transmisionrepo.findById(Integer.parseInt(id));
        if(tOptional.isPresent()){
            Transmision transmisione = tOptional.get();
            return new ResponseEntity<>(transmisione, HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Transmision editarTransmision) {
         Optional<Transmision> tOptional = transmisionrepo.findById(Integer.parseInt(id));
        if(tOptional.isPresent()){
            Transmision transmisione = tOptional.get();
            editarTransmision.setIdTransmision(transmisione.getIdTransmision());
            transmisionrepo.save(editarTransmision);
            return new ResponseEntity<>(editarTransmision, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
        
        }
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody Transmision nuevaTransmision) {
      nuevaTransmision = transmisionrepo.save(nuevaTransmision);
         Optional<Transmision> tOptional = transmisionrepo.findById(nuevaTransmision.getIdTransmision());
     if(tOptional.isPresent()){
         Transmision transmisione = tOptional.get();
      return new ResponseEntity<>(transmisione,HttpStatus.CREATED);
     }else{
     return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);     
     }
       
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
         Optional<Transmision> tOptional = transmisionrepo.findById(Integer.parseInt(id));
       
       if(tOptional.isPresent()){
           Transmision transmisione = tOptional.get();
           transmisionrepo.deleteById(transmisione.getIdTransmision());
           return new ResponseEntity<>(transmisione,HttpStatus.OK);
       }else{
       
       return new ResponseEntity<>(null,HttpStatus.EXPECTATION_FAILED);
       }
       
    }
    
}
