/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inacap.webcomponet.pagina.controler;

import inacap.webcomponet.pagina.model.Modelo;
import inacap.webcomponet.pagina.repository.ModeloIR;
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
@RequestMapping("/modelo")
public class ModeloController {
   

    
    @Autowired
    private ModeloIR modeloepo;
    
    @GetMapping()
    public Iterable<Modelo> list() {
       return modelorepo.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Modelo> get(@PathVariable String id) {
        Optional<Modelo> modeloop = modelorepo.findById(Integer.parseInt(id));
        if(modeloop.isPresent()){
            Modelo modeloe = modeloop.get();
            return new ResponseEntity<>(mordeloe, HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Modelo> put(@PathVariable String id, @RequestBody Modelo editarModelo) {
        Optional<Modelo> maOptional = modelorepo.findById(Integer.parseInt(id));
        if(maOptional.isPresent()){
            Modelo modeloe = maOptional.get();
            editarModelo.setIdModelo(modeloe.getIdModelo());
            modelorepo.save(editarModelo);
            return new ResponseEntity<>(editarModelo, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
        
        }
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody Modelo nuevaModelo) {
      nuevaModelo = modelorepo.save(nuevaModelo);
         Optional<Modelo> mOptional = modelorepo.findById(nuevaModelo.getIdModelo());
     if(mOptional.isPresent()){
         Modelo modeloe = mOptional.get();
      return new ResponseEntity<>(modeloe,HttpStatus.CREATED);
     }else{
     return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);     
     }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
       Optional<Modelo> maOptional = modelorepo.findById(Integer.parseInt(id));
       
       if(maOptional.isPresent()){
           Modelo modeloe = maOptional.get();
           modelorepo.deleteById(modeloe.getIdModelo());
           return new ResponseEntity<>(modeloe,HttpStatus.OK);
       }else{
       
       return new ResponseEntity<>(null,HttpStatus.EXPECTATION_FAILED);
       }
       
    }
    
}
 
