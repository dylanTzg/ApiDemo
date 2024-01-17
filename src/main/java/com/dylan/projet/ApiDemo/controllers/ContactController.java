package com.dylan.projet.ApiDemo.controllers;

import com.dylan.projet.ApiDemo.models.Contact;
import com.dylan.projet.ApiDemo.services.interfaces.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService contactService;

    public ResponseEntity<Integer> save(@RequestBody Contact contact) {
        return ResponseEntity.ok(contactService.save(contact));
    }

    @GetMapping("/")
    public ResponseEntity<List<Contact>> findAll() {
        return ResponseEntity.ok(contactService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(contactService.findById(id));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<List<Contact>> findByAllUserId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(contactService.findAllByUserId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
        contactService.deleteById(id);
        return ResponseEntity.accepted().build();
    }

}
