package com.dylan.projet.ApiDemo.controllers;

import com.dylan.projet.ApiDemo.models.Account;
import com.dylan.projet.ApiDemo.services.interfaces.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    public ResponseEntity<Integer> save(@RequestBody Account account) {
        return ResponseEntity.ok(accountService.save(account));
    }

    @GetMapping("/")
    public ResponseEntity<List<Account>> findAll() {
        return ResponseEntity.ok(accountService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(accountService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
        accountService.deleteById(id);
        return ResponseEntity.accepted().build();
    }
}
