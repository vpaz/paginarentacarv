/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inacap.webcomponet.pagina.repository;

import org.springframework.data.repository.CrudRepository;
import inacap.webcomponet.pagina.model.Marca;

/**
 *
 * @author Vale
 */
public interface MarcaIR extends CrudRepository<Marca, Integer> {
    
}
