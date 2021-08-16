package com.manager.booklibrary.controller;

import java.security.Principal;

import com.manager.booklibrary.entity.Reader;
import com.manager.booklibrary.repository.AppUserRepository;
import com.manager.booklibrary.service.ReaderService;

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
@RequestMapping("/api/readers")
public class ReaderController {
    
    @Autowired
    private ReaderService readerService;

    @Autowired
    private AppUserRepository appUserRepository;

    @GetMapping("")
    public ResponseEntity<?> getReader(Principal principal) {
        long id = appUserRepository.findByUsername(principal.getName()).getId();
        return ResponseEntity.ok().body(readerService.getReaderByIdUser(id));
    }

    @PostMapping("/save")
    public ResponseEntity<?> addReader(@RequestBody Reader reader) {
        readerService.addReader(reader);
        return new ResponseEntity<>("Adding success", HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteReader(@PathVariable long id) {
        readerService.deleteReader(id);
        return new ResponseEntity<>("Delete success", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateReader(@RequestBody Reader reader) {
        readerService.updateReader(reader);
        return new ResponseEntity<>("Update success", HttpStatus.OK);
    }

}
