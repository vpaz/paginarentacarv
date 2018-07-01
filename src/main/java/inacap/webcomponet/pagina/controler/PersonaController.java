/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inacap.webcomponet.pagina.controler;


import inacap.webcomponent.pagina.model.Persona;
import inacap.webcomponent.pagina.repository.RegionIR;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author Gonzalo
 */
@RestController
@RequestMapping("/persona")
public class PersonaController {
    
    @GetMapping()
    public List<Persona> list() {
        return Persona.listaPersona;
    }
    
    @GetMapping("/{id}")
    public Persona get(@PathVariable String id) {
        Persona persona = new Persona();
        
        return persona.buscaPersona(Integer.parseInt(id));
    }
    

  
     @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Persona editarPersona) {
     Persona persona = new Persona();
     return new ResponseEntity<>(persona.editarPersona(Integer.parseInt(id), editarPersona), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody Persona nuevaPersona) {
      //responseentity es un estado recibe el objeto
      Persona persona = new Persona();
     if( persona.nuevaPersona(nuevaPersona)){
      return new ResponseEntity<>(HttpStatus.CREATED);
     
     }else{
     return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);     
     }
     
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
         Persona persona = new Persona();
       
       if(persona.eliminarPersona(Integer.parseInt(id))){
           return new ResponseEntity<>(HttpStatus.OK);
       }else{
       
       return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
       }
    }
    
}
