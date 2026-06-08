package com.trainingmug.ecommerce.controller;

import com.trainingmug.ecommerce.exception.CustomerExistsException;
import com.trainingmug.ecommerce.exception.CustomerNotFoundException;
import com.trainingmug.ecommerce.model.Customer;
import com.trainingmug.ecommerce.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    //CRUD operations (End Points)
    //Save ( POST -> body)
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Customer customer) {
        //1. Throw CustomerExistsException if customer exists
        //2. save customer
        //3. return saved customer
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(customerService.save(customer));
        } catch (CustomerExistsException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
        //ResponseEntity Types
        /*
        201 Created -> ResponseEntity<Customer>
        409 Conflict -> ResponseEntity<String>
        500 Internal Server Error -> ResponseEntity<String>
         */
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAll(){
        //GET -> 200 Ok
        //ResonseEntity.status(HttpStatus.OK).body(customerService.getAll());
        return ResponseEntity.ok(customerService.getAll());
    }
    //http://localhost:8080/api/customers/1
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        try {
            return ResponseEntity.ok(customerService.getById(id));
        } catch(CustomerNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Customer customer){
        try {
            return ResponseEntity.ok(customerService.update(customer));
        } catch(CustomerNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        try {
            customerService.delete(id);
            return ResponseEntity.noContent().build();
        } catch(CustomerNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    /*
    http://localhost:8080/api/customers?id=1
    @DeleteMapping
    public ResponseEntity<?> deleteById(@RequestParam int id){}
     */


}
