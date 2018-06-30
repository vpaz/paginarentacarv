/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inacap.webcomponet.pagina.controler;

import inacap.webcomponet.pagina.model.Combustible;
import inacap.webcomponet.pagina.repository.CombustibleIR;
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
@RequestMapping("/combustible")
public class CombustibleController {
    

    @Autowired
   private CombustibleIR combustiblerepositorio;
    
    @GetMapping()
    public Iterable<Combustible> list() {
        return combustiblerepositorio.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Combustible> get(@PathVariable String id) {
        
        Optional<Combustible> combustibleop = combustiblerepositorio.findById(Integer.parseInt(id));
        if(combustibleop.isPresent()){
            Combustible combustiblee = combustibleop.get();
            return new ResponseEntity<>(combustiblee, HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Combustible> put(@PathVariable String id, @RequestBody Combustible editarCombustible) {
        Optional<Combustible> combustibleop = combustiblerepositorio.findById(Integer.parseInt(id));
        if(combustibleop.isPresent()){
            Combustible combustiblee = combustibleop.get();
            editarCombustible.setIdCombustible(combustiblee.getIdCombustible());
            combustiblerepositorio.save(editarCombustible);
            return new ResponseEntity<>(editarCombustible, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
        
        }
        
        
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody Combustible nuevoCombustible) {
         nuevoCombustible = combustiblerepositorio.save(nuevoCombustible);
         Optional<Combustible> combustibleop = combustiblerepositorio.findById(nuevoCombustible.getIdCombustible());
     if(combustibleop.isPresent()){
         Combustible combustiblee = combustibleop.get();
      return new ResponseEntity<>(combustiblee,HttpStatus.CREATED);
     }else{
     return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);     
     }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
      Optional<Combustible> combustibleop = combustiblerepositorio.findById(Integer.parseInt(id));
       
       if(combustibleop.isPresent()){
           Combustible combustiblee = combustibleop.get();
           combustiblerepositorio.deleteById(combustiblee.getIdCombustible());
           return new ResponseEntity<>(combustiblee,HttpStatus.OK);
       }else{
       
       return new ResponseEntity<>(null,HttpStatus.EXPECTATION_FAILED);
       }
       
    
    }
}
