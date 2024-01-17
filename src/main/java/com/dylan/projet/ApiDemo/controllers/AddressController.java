package com.dylan.projet.ApiDemo.controllers;

import com.dylan.projet.ApiDemo.models.Address;
import com.dylan.projet.ApiDemo.services.interfaces.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;
    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody Address address) {
        return ResponseEntity.ok(addressService.save(address));
    }

    @GetMapping("/")
    public  ResponseEntity<List<Address>> findAll() {
        return ResponseEntity.ok(addressService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(addressService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
        addressService.deleteById(id);
        return ResponseEntity.accepted().build();
    }

}
