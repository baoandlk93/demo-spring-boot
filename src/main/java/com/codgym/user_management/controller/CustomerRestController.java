package com.codgym.user_management.controller;

import com.codgym.user_management.entity.Customer;
import com.codgym.user_management.service.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
@AllArgsConstructor
@CrossOrigin("*")
public class CustomerRestController {
    private final ICustomerService customerService;

    @GetMapping
    public ResponseEntity<Page<Customer>> findAll(@PageableDefault(page = 0,size = 10,sort = "id",direction = Sort.Direction.DESC ) Pageable pageable) {
        return new ResponseEntity<>(customerService.findAll(pageable), null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        customerService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Customer customer) {
        customerService.save(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
