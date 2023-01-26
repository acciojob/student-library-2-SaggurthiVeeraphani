package com.driver.controller;

import com.driver.models.Author;
import com.driver.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Add required annotations
@RestController
@RequestMapping("/Author")
public class AuthorController {
    @Autowired
    AuthorService authorservice;


    //Write createAuthor API with required annotations
    @PostMapping("/create")
    public ResponseEntity<String> createAuthor(@RequestBody Author author){
       authorservice.create(author);
       return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

}
