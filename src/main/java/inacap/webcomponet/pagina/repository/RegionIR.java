/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inacap.webcomponet.pagina.repository;

import org.springframework.data.repository.CrudRepository;
import inacap.webcomponet.pagina.model.Region;


public interface RegionIR extends CrudRepository<Region, Integer> {
    
}
