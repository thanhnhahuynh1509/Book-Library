package com.manager.booklibrary.repository;

import java.util.List;

import com.manager.booklibrary.entity.Author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("Select a from Author a where a.name like %?1% or a.nickname like %?1%")
    public List<Author> findAuthorsByName(String name);

}
