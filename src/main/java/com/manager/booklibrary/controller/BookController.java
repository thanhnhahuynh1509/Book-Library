package com.manager.booklibrary.controller;

import java.sql.Date;

import com.manager.booklibrary.entity.Book;
import com.manager.booklibrary.service.BookService;

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
@RequestMapping("/api/books")
public class BookController {
    
    @Autowired
    BookService bookService;

    @GetMapping("") // done
    public ResponseEntity<?> getBooks() {
        return ResponseEntity.ok().body(bookService.getBooks());
    }

    @GetMapping("/name/{name}") // done
    public ResponseEntity<?> getBooksByName(@PathVariable String name) {
        return ResponseEntity.ok().body(bookService.getBooksByNameLike(name));
    }
    
    @GetMapping("/date/{publicDate}") // done
    public ResponseEntity<?> getBooksByDate(@PathVariable Date publicDate) {
        return ResponseEntity.ok().body(bookService.getBooksByPublicDate(publicDate));
    }
    
    @GetMapping("/price-less-than/{price}") // done
    public ResponseEntity<?> getBooksByPriceLessThan(@PathVariable float price) {
        return ResponseEntity.ok().body(bookService.getBooksByPriceLessThan(price));
    }
    
    @GetMapping("/price-higher-than/{price}") // done
    public ResponseEntity<?> getBooksByPriceHigherThan(@PathVariable float price) {
        return ResponseEntity.ok().body(bookService.getBooksByPriceHigherThan(price));
    }

    @GetMapping("/author/{name}") // done
    public ResponseEntity<?> getBooksByAuthor(@PathVariable String name) {
        return ResponseEntity.ok().body(bookService.getBooksByAuthor(name));
    }

    @PostMapping("/save") // done
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        bookService.addBook(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @PutMapping("/update") // done
    public ResponseEntity<?> updateBook(@RequestBody Book book) {
        bookService.updateBook(book);
        return new ResponseEntity<>("Update success!", HttpStatus.OK);
    }

    @PutMapping("/update/{bookId}/author/{authorId}") // done
    public ResponseEntity<?> addAuthorIntoBook(@PathVariable long authorId, @PathVariable long bookId) {
        bookService.addAuthorIntoBook(bookId, authorId);
        return new ResponseEntity<>("Update success!", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}") // done
    public ResponseEntity<?> deleteBook(@PathVariable long id) {
        bookService.deleteBookById(id);
        return new ResponseEntity<>("Delete success!", HttpStatus.CREATED);
    }    


    

}
