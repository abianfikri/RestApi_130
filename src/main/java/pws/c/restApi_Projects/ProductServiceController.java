/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pws.c.restApi_Projects;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Abian
 */
@RestController
public class ProductServiceController {
    private static HashMap<String, Product > productRepo = new HashMap<>();
    
    static{
        Product honey = new Product();
        honey.setId("1");
        honey.setName("Madu");
        honey.setPrice("5000");
        honey.setQty("6");
        productRepo.put(honey.getId(), honey);
        
        Product almond = new Product();
        almond.setId("2");
        almond.setName("almond");
        almond.setPrice("6000");
        almond.setQty("7");
        productRepo.put(almond.getId(), almond);
    }
    
    // Get API
    @RequestMapping(value="/products")
    public ResponseEntity<Object> getProduct(){
       return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }
    
    // Post API
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@RequestBody Product product){
        
        if(productRepo.containsKey(product.getId())){
            return new ResponseEntity<>("ID Product "  + product.getId().toString() +" Cannot be the Same" +",Please check again", HttpStatus.OK);
        }
        else{
            productRepo.put(product.getId(), product);
            return new ResponseEntity<>("Product is created Successfully", HttpStatus.CREATED);
        }
    }
    
    // PUT API
    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product){
        
        if(!productRepo.containsKey(id)){
            return new ResponseEntity<>("Product Not Found to updated ID " + id +" ,Please check again", HttpStatus.NOT_FOUND);
        }
        else{
            productRepo.remove(id);
            product.setId(id);
            productRepo.put(id, product);
            return new  ResponseEntity<>("Product is updated Successfully",HttpStatus.OK);
        }
        
    }
    
    // DELETE API
    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") String id){
        
        if(!productRepo.containsKey(id)){
            return new ResponseEntity<>("Product Not Found to deleted " + id +" ,Please check again", HttpStatus.NOT_FOUND);
        }
        else{
            productRepo.remove(id);
            return new ResponseEntity<>("Product is deleted successfully", HttpStatus.OK);
        }
        
        
    }
    
}
