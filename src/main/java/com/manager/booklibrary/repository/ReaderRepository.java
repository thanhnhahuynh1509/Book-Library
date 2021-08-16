package com.manager.booklibrary.repository;

import com.manager.booklibrary.entity.Reader;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {

    @Query("select r from Reader r inner join r.appUser a where a.id = ?1")
    Reader findReaderByIdUser(long id);
}
