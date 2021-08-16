package com.manager.booklibrary.repository;

import java.sql.Date;
import java.util.List;

import com.manager.booklibrary.entity.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    @Query("Select b from Book b where b.name like %?1%")
    public List<Book> findBookByNameLike(String name);

    @Query("Select b from Book b where b.publicDate = ?1")
    public List<Book> findBookByPublicDate(Date publicDate);

    @Query("Select b from Book b where b.price <= ?1 order by price desc")
    public List<Book> findBookByPriceLessThan(float price);

    @Query("Select b from Book b where b.price >= ?1 order by price asc")
    public List<Book> findBookByPriceHigherThan(float price);

    @Query("Select b from Book b inner join b.authors a where a.name like %?1% or a.nickname like %?1%")
    public List<Book> findBookByAuthor(String name);

}
