
package inacap.webcomponet.pagina.controler;

import inacap.webcomponet.pagina.model.TipoVehiculo;
import inacap.webcomponet.pagina.repository.TipoVehiculoIR;
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
@RequestMapping("/tipovehiculo")
public class TipoVehiculoController {
    

    @Autowired
   private TipoVehiculoIR tvrepo;
    
    @GetMapping()
    public Iterable<TipoVehiculo> list() {
        return tvrepo.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TipoVehiculo> get(@PathVariable String id) {
       Optional<TipoVehiculo> tvOptional = tvrepo.findById(Integer.parseInt(id));
        if(tvOptional.isPresent()){
            TipoVehiculo tve = tvOptional.get();
            return new ResponseEntity<>(tve, HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<TipoVehiculo> put(@PathVariable String id, @RequestBody TipoVehiculo tipovehiculoeditar)
    { Optional<TipoVehiculo> tvOptional = tvrepo.findById(Integer.parseInt(id));
        if(tvOptional.isPresent()){
            TipoVehiculo tve = tvOptional.get();
            tipovehiculoeditar.setIdTipoVehiculo(tve.getIdTipoVehiculo());
            tvrepo.save(tipovehiculoeditar);
            return new ResponseEntity<>(tipovehiculoeditar, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
        
        }
    }
    @PostMapping
    public ResponseEntity<?> post(@RequestBody TipoVehiculo nuevoTipoVehiculo) {
    nuevoTipoVehiculo = tvrepo.save(nuevoTipoVehiculo);
    Optional<TipoVehiculo> tvOptional = tvrepo.findById(nuevoTipoVehiculo.getIdTipoVehiculo());
     if(tvOptional.isPresent()){
         TipoVehiculo tve = tvOptional.get();
      return new ResponseEntity<>(tve,HttpStatus.CREATED);
     }else{
     return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);     
     }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
    Optional<TipoVehiculo> tvOptional = tvrepo.findById(Integer.parseInt(id));
       
       if(tvOptional.isPresent()){
           TipoVehiculo tve = tvOptional.get();
           tvrepo.deleteById(tve.getIdTipoVehiculo());
           return new ResponseEntity<>(tve,HttpStatus.OK);
       }else{
       
       return new ResponseEntity<>(null,HttpStatus.EXPECTATION_FAILED);
       }
       
    }
    
}

