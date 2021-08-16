package com.manager.booklibrary.repository;

import javax.transaction.Transactional;

import com.manager.booklibrary.entity.Cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    
    @Query("Select sum(b.price) from Cart c inner join c.books b where c.id = ?1  group by c.id")
    Float totalMoney(long cartId);

    @Modifying
    @Transactional
    @Query(value = "Delete from cart_books cb where cb.cart_id = ?2 and cb.books_id = ?1", nativeQuery = true)
    Integer deleteBookOutCart(long bookId, long cartId);

}
