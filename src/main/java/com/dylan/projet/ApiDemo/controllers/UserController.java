package com.dylan.projet.ApiDemo.controllers;

import com.dylan.projet.ApiDemo.models.User;
import com.dylan.projet.ApiDemo.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<Integer> save( @RequestBody User user) {
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }
    @GetMapping("/findByEmail")
    public ResponseEntity<User> findByEmail
            (@RequestParam("email") String email) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findIdByEmail(email));
    }

    @GetMapping("/find")
    public ResponseEntity<List<User>> findByFirstNameAndLastNameAndBirthDate
            (@RequestParam("firstName") String firstName,
             @RequestParam("lastName") String lastName,
             @RequestParam("birthDate") String birthDate) {
        LocalDate birthLocalDate = LocalDate.parse(birthDate);
        return ResponseEntity.status(HttpStatus.OK).body(userService.findByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndBirthDate(firstName, lastName, birthLocalDate));
    }

    @PatchMapping("/validate/{id}")
    public ResponseEntity<Integer> validateAccount(@PathVariable("id") Integer id) {
        userService.validateAccount(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/invalidate/{id}")
    public ResponseEntity<Integer> invalidateAccount(@PathVariable("id") Integer id) {
        userService.invalidateAccount(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteById(@PathVariable("id") Integer id) {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
