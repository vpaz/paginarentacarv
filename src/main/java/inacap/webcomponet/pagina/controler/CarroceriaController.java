/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inacap.webcomponet.pagina.controler;

import inacap.webcomponet.pagina.model.Carroceria;
import inacap.webcomponet.pagina.repository.CarroceriaIR;
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
@RequestMapping("/carroceria")
public class CarroceriaController {
   
    @Autowired
    private CarroceriaIR carroceriarepo;
    @GetMapping()
    public Iterable<Carroceria> list() {
        return carroceriarepo.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Carroceria> get(@PathVariable String id) {
       Optional<Carroceria> carroceriaop = carroceriarepo.findById(Integer.parseInt(id));
        if(carroceriaop.isPresent()){
            Carroceria carroceriae = carroceriaop.get();
            return new ResponseEntity<>(carroceriae, HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Carroceria> put(@PathVariable String id, @RequestBody Carroceria editarCarroceria) {
         Optional<Carroceria> caOptional = carroceriarepo.findById(Integer.parseInt(id));
        if(caOptional.isPresent()){
            Carroceria carroceriae = caOptional.get();
            editarCarroceria.setIdCarroceria(carroceriae.getIdCarroceria());
            carroceriarepo.save(editarCarroceria);
            return new ResponseEntity<>(editarCarroceria, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
        
        }
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody Carroceria nuevaCarroceria) {
      nuevaCarroceria = carroceriarepo.save(nuevaCarroceria);
         Optional<Carroceria> caOptional = carroceriarepo.findById(nuevaCarroceria.getIdCarroceria());
     if(caOptional.isPresent()){
         Carroceria carroceriae = caOptional.get();
      return new ResponseEntity<>(carroceriae,HttpStatus.CREATED);
     }else{
     return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);     
     }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
      Optional<Carroceria> caOptional = carroceriarepo.findById(Integer.parseInt(id));
       
       if(caOptional.isPresent()){
           Carroceria carroceriae = caOptional.get();
           carroceriarepo.deleteById(carroceriae.getIdCarroceria());
           return new ResponseEntity<>(carroceriae,HttpStatus.OK);
       }else{
       
       return new ResponseEntity<>(null,HttpStatus.EXPECTATION_FAILED);
       }
       
       
    }
    
}

