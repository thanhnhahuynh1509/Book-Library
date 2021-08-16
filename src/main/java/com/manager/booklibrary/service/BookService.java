package com.manager.booklibrary.service;

import java.sql.Date;
import java.util.List;

import com.manager.booklibrary.entity.Book;

public interface BookService {
    
    public List<Book> getBooks();

    public List<Book> getBooksByNameLike(String name);

    public List<Book> getBooksByPublicDate(Date publicDate);

    public List<Book> getBooksByPriceLessThan(float price);

    public List<Book> getBooksByPriceHigherThan(float price);

    public List<Book> getBooksByAuthor(String name);

    public void addBook(Book book);

    public void updateBook(Book book);

    public void deleteBookById(long id);

    public void addAuthorIntoBook(long bookId, long authorId);

}
