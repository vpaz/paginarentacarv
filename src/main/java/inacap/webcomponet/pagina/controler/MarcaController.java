/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inacap.webcomponet.pagina.controler;

import inacap.webcomponet.pagina.model.Marca;
import inacap.webcomponet.pagina.repository.MarcaIR;
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
@RequestMapping("/marca")
public class MarcaController {
   

    
    @Autowired
    private MarcaIR marcarepo;
    
    @GetMapping()
    public Iterable<Marca> list() {
       return marcarepo.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Marca> get(@PathVariable String id) {
        Optional<Marca> marcaop = marcarepo.findById(Integer.parseInt(id));
        if(marcaop.isPresent()){
            Marca marcae = marcaop.get();
            return new ResponseEntity<>(marcae, HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Marca> put(@PathVariable String id, @RequestBody Marca editarMarca) {
        Optional<Marca> maOptional = marcarepo.findById(Integer.parseInt(id));
        if(maOptional.isPresent()){
            Marca marcae = maOptional.get();
            editarMarca.setIdMarca(marcae.getIdMarca());
            marcarepo.save(editarMarca);
            return new ResponseEntity<>(editarMarca, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
        
        }
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody Marca nuevaMarca) {
      nuevaMarca = marcarepo.save(nuevaMarca);
         Optional<Marca> mOptional = marcarepo.findById(nuevaMarca.getIdMarca());
     if(mOptional.isPresent()){
         Marca marcae = mOptional.get();
      return new ResponseEntity<>(marcae,HttpStatus.CREATED);
     }else{
     return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);     
     }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
       Optional<Marca> maOptional = marcarepo.findById(Integer.parseInt(id));
       
       if(maOptional.isPresent()){
           Marca marcae = maOptional.get();
           marcarepo.deleteById(marcae.getIdMarca());
           return new ResponseEntity<>(marcae,HttpStatus.OK);
       }else{
       
       return new ResponseEntity<>(null,HttpStatus.EXPECTATION_FAILED);
       }
       
    }
    
}
 

