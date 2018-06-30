/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inacap.webcomponet.pagina.controler;

import inacap.webcomponet.pagina.model.MedioPago;
import inacap.webcomponet.pagina.repository.MedioPagoIR;
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
@RequestMapping("/mediopago")
public class MedioPagoController {
   

    
    @Autowired
    private MedioPagoIR mediopagorepo;
    
    @GetMapping()
    public Iterable<MedioPago> list() {
        return mediopagorepo.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MedioPago> get(@PathVariable String id) {
         Optional<MedioPago> mpOptional = mediopagorepo.findById(Integer.parseInt(id));
        if(mpOptional.isPresent()){
            MedioPago mediopagoe = mpOptional.get();
            return new ResponseEntity<>(mediopagoe, HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<MedioPago> put(@PathVariable String id, @RequestBody MedioPago editaMedioPago) {
    
        Optional<MedioPago> mpOptional = mediopagorepo.findById(Integer.parseInt(id));
        if(mpOptional.isPresent()){
            MedioPago mediopagoe = mpOptional.get();
            editaMedioPago.setIdMedioPago(mediopagoe.getIdMedioPago());
            mediopagorepo.save(editaMedioPago);
            return new ResponseEntity<>(editaMedioPago, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
        
        }
    
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody MedioPago nuevoMedioPago) {
        
     nuevoMedioPago = mediopagorepo.save(nuevoMedioPago);
         Optional<MedioPago> mpOptional = mediopagorepo.findById(nuevoMedioPago.getIdMedioPago());
     if(mpOptional.isPresent()){
         MedioPago mediopagoe = mpOptional.get();
      return new ResponseEntity<>(mediopagoe,HttpStatus.CREATED);
     }else{
     return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);     
     }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        
       Optional<MedioPago> mpOptional = mediopagorepo.findById(Integer.parseInt(id));
       
       if(mpOptional.isPresent()){
           MedioPago mediopagoe = mpOptional.get();
           mediopagorepo.deleteById(mediopagoe.getIdMedioPago());
           return new ResponseEntity<>(mediopagoe,HttpStatus.OK);
       }else{
       
       return new ResponseEntity<>(null,HttpStatus.EXPECTATION_FAILED);
       }
       
    
    }
    
}
 

