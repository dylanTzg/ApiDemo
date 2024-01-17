package com.dylan.projet.ApiDemo.controllers;

import com.dylan.projet.ApiDemo.models.Transaction;
import com.dylan.projet.ApiDemo.services.interfaces.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transactions")
public class TransactionController {


    private final TransactionService transactionService;

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody Transaction transaction) {
        return ResponseEntity.ok(transactionService.save(transaction));
    }

    @GetMapping("/")
    public ResponseEntity<List<Transaction>> findAll() {
        return ResponseEntity.ok(transactionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(transactionService.findById(id));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<List<Transaction>> findAllByUserId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(transactionService.findAllByUserId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
        transactionService.deleteById(id);
        return ResponseEntity.accepted().build();
    }
}
