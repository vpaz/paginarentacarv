/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inacap.webcomponet.pagina.controler;

import inacap.webcomponet.pagina.model.Ciudad;
import inacap.webcomponet.pagina.repository.CiudadIR;
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
@RequestMapping("/ciudad")
public class ciudadController {
   
    @Autowired
    private CiudadIR ciudadrepo;
    @GetMapping()
    public Iterable<Ciudad> list() {
        return ciudadrepo.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Ciudad> get(@PathVariable String id) {
       Optional<Ciudad> ciudadaop = ciudadrepo.findById(Integer.parseInt(id));
        if(ciudadop.isPresent()){
            Ciudad ciudade = ciudadop.get();
            return new ResponseEntity<>(ciudade, HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Ciudad> put(@PathVariable String id, @RequestBody Ciudad editarCiudad) {
         Optional<Ciudad> caOptional = ciudadrepo.findById(Integer.parseInt(id));
        if(caOptional.isPresent()){
            Ciudad ciudade = caOptional.get();
            editarCiudad.setIdCiudad(ciudade.getIdCiudad());
            ciudadrepo.save(editarCiudad);
            return new ResponseEntity<>(editarCiudad, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
        
        }
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody Ciudad nuevaCiudad) {
      nuevaCiudad = ciudadrepo.save(nuevaCiudad);
         Optional<Ciudad> caOptional = ciudadrepo.findById(nuevaCiudada.getIdCiudad());
     if(caOptional.isPresent()){
         Ciudad ciudadae = caOptional.get();
      return new ResponseEntity<>(ciudade,HttpStatus.CREATED);
     }else{
     return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);     
     }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
      Optional<Ciudad> caOptional = ciudadrepo.findById(Integer.parseInt(id));
       
       if(caOptional.isPresent()){
           Ciudad ciudade = caOptional.get();
           ciudadrepo.deleteById(ciudade.getIdCiudad());
           return new ResponseEntity<>(ciudade,HttpStatus.OK);
       }else{
       
       return new ResponseEntity<>(null,HttpStatus.EXPECTATION_FAILED);
       }
       
       
    }
    
}