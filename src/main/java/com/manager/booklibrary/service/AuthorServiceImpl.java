package com.manager.booklibrary.service;

import java.util.List;

import com.manager.booklibrary.entity.Author;
import com.manager.booklibrary.exception.AlreadyException;
import com.manager.booklibrary.exception.NotFoundException;
import com.manager.booklibrary.repository.AuthorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Author> getAuthors() {
        log.info("Getting all authors");
        return authorRepository.findAll();
    }

    @Override
    public List<Author> getAuthorsByName(String name) {
        log.info("Getting all authors with name is {}", name);
        return authorRepository.findAuthorsByName(name);
    }

    @Override
    public void addAuthor(Author author) {
        if (authorRepository.findById(author.getId()).isPresent()) {
            log.error("Save failed: author: {}", author);
            throw new AlreadyException("Author is already");
        } else {
            log.info("Saving new author into database with name is {}", author);
            authorRepository.save(author);
        }
    }

    @Override
    public void deleteAuthor(long id) {
        boolean checkAuthorExists = authorRepository.findById(id).isPresent();
        if (!checkAuthorExists) {
            log.info("Deleted failed: not found author with id: {}", id);
            throw new NotFoundException("Not found author please! check again");
        } else {
            log.info("Deleting author with id: {}", id);
            authorRepository.deleteById(id);
        }
    }

    @Override
    public void updateAuthor(Author author) {
        boolean checkAuthorExists = authorRepository.findById(author.getId()).isPresent();
        if (!checkAuthorExists) {
            log.info("Updated failed: not found author with id: {}", author.getId());
            throw new NotFoundException("Not found author please! check again");
        } else {
            log.info("Updating author with id: {}", author.getId());
            authorRepository.save(author);
        }
    }
    
}
