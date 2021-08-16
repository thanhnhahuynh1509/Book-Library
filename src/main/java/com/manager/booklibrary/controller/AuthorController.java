package com.manager.booklibrary.controller;

import com.manager.booklibrary.entity.Author;
import com.manager.booklibrary.service.AuthorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    
    @Autowired
    private AuthorService authorService;

    @GetMapping("")
    public ResponseEntity<?> getAuthors() {
        return ResponseEntity.ok().body(authorService.getAuthors());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getAuthorsByName(@PathVariable String name) {
        return ResponseEntity.ok().body(authorService.getAuthorsByName(name));
    }

    @PostMapping("/save")
    public ResponseEntity<?> addAuthor(@RequestBody Author author) {
        authorService.addAuthor(author);
        return new ResponseEntity<>("Add success", HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAuthor(@RequestBody Author author) {
        authorService.updateAuthor(author);
        return ResponseEntity.ok().build();
    }    

}
