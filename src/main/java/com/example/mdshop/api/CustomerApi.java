package com.example.mdshop.api;

import com.example.mdshop.dto.CustomerChangeEnabledDto;
import com.example.mdshop.model.Customer;
import com.example.mdshop.repository.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerApi {
    private CustomerRepository repository;
    public CustomerApi(CustomerRepository repository) {
        this.repository = repository;
    }
    @GetMapping("/by-name")
    public List<Customer> getByName(@RequestParam(name = "name") String name){
        return repository.findByName(name);
    }
    @GetMapping
    public Iterable<Customer> getAll(){
        return repository.findAll();
    }
    @PutMapping("/block")

    public int changeEnabled(@RequestBody CustomerChangeEnabledDto updateDto){
        return repository.changeEnabled(updateDto.id,updateDto.enabled);
    }
}
