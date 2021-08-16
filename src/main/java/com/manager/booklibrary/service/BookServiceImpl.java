package com.manager.booklibrary.service;

import java.sql.Date;
import java.util.List;

import com.manager.booklibrary.entity.Author;
import com.manager.booklibrary.entity.Book;
import com.manager.booklibrary.exception.NotFoundException;
import com.manager.booklibrary.repository.AuthorRepository;
import com.manager.booklibrary.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Book> getBooks() {
        log.info("Getting all books");
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getBooksByNameLike(String name) {
        log.info("Getting books with name like {}", name);
        return bookRepository.findBookByNameLike(name);
    }

    @Override
    public List<Book> getBooksByPublicDate(Date publicDate) {
        log.info("Getting books with public date like {}", publicDate);
        return bookRepository.findBookByPublicDate(publicDate);
    }

    @Override
    public List<Book> getBooksByPriceLessThan(float price) {
        log.info("Getting books with price less than {}", price);
        return bookRepository.findBookByPriceLessThan(price);
    }

    @Override
    public List<Book> getBooksByPriceHigherThan(float price) {
        log.info("Getting books with price higher than {}", price);
        return bookRepository.findBookByPriceHigherThan(price);
    }

    @Override
    public List<Book> getBooksByAuthor(String name) {
        log.info("Getting books with author has name like {}", name);
        return bookRepository.findBookByAuthor(name);
    }

    @Override
    public void addBook(Book book) {
        log.info("Adding new book to database with name is {}", book.getName());
        bookRepository.save(book);
    }

    @Override
    public void updateBook(Book book) {
        boolean checkBookExists = bookRepository.findById(book.getId()).isPresent();

        if (!checkBookExists) {
            log.error("Updated book with id: {} failed", book.getId());
            throw new NotFoundException("Not found book with id: " + book.getId());
        } else {
            log.info("Updating book with id is {}", book.getId());
            bookRepository.save(book);
        }
    }

    @Override
    public void deleteBookById(long id) {
        boolean checkBookExists = bookRepository.findById(id).isPresent();

        if (!checkBookExists) {
            log.error("Deleted book with id: {} failed", id);
            throw new NotFoundException("Not found book with id: " + id);
        } else {
            log.info("Deleting book with id is {}", id);
            bookRepository.deleteById(id);
        }
    }

    @Override
    public void addAuthorIntoBook(long bookId, long authorId) {
        Book book = bookRepository.findById(bookId).get();
        Author author = authorRepository.findById(authorId).get();
        
        if (book == null || author == null) {
            log.error("Added failed! not found book with id: {} or author with id: {}", bookId, authorId);
            throw new NotFoundException("Not found book with id: " + bookId + " or author with id: " + authorId);
        }

        log.info("Saving author with name is {} into book with name is {}"
                , author.getName()
                , book.getName());
                
        book.getAuthors().add(author);
        bookRepository.save(book);
    }
    
}
