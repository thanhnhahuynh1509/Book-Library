package com.manager.booklibrary.service;

import java.util.List;

import com.manager.booklibrary.entity.Author;

public interface AuthorService {

    List<Author> getAuthors();

    List<Author> getAuthorsByName(String name);

    void addAuthor(Author author);

    void deleteAuthor(long id);

    void updateAuthor(Author author);

}
