package com.manager.booklibrary.service;

import com.manager.booklibrary.entity.Book;
import com.manager.booklibrary.entity.Cart;
import com.manager.booklibrary.exception.NotFoundException;
import com.manager.booklibrary.repository.BookRepository;
import com.manager.booklibrary.repository.CartRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookRepository bookRepository;


    private void updateTotalMoneyForCart(long id, float plus) {
        Cart cart = cartRepository.findById(id).get();
        float totalMoney = 0;
        if (cartRepository.totalMoney(id) != null) {
            totalMoney = cartRepository.totalMoney(id);
        }
        totalMoney += plus;
        log.info("Set total money: {} for cart", totalMoney);
        cart.setTotalMoney(totalMoney);
        cartRepository.save(cart);
    }

    @Override
    public Cart getCart(long id) {
        if (!cartRepository.findById(id).isPresent()) {
            log.error("Get failed: not found cart with id: " + id);
            throw new NotFoundException("Not found cart with id: " + id);
        } else {
            Cart cart = cartRepository.findById(id).get();
            log.info("Getting cart with id: {}", id);
            return cart;
        }
    }


    @Override
    public void addBookIntoCart(long bookId, long cartId) {
        Book book = bookRepository.findById(bookId).get();
        Cart cart = cartRepository.findById(cartId).get();

        if (book == null || cart == null) {
            log.error("Add failed: because book = {} and cart = {}", book, cart);
            throw new NotFoundException("Not found book with id: " + bookId + " or cart with id: " + cartId);
        } else {
            updateTotalMoneyForCart(cartId, book.getPrice());
            log.info("Saving book into cart");
            cart.getBooks().add(book);
            cartRepository.save(cart);
        }
    }

    @Override
    public void deleteBookOutCart(long bookId, long cartId) {
        log.info("Delete book out cart");
        cartRepository.deleteBookOutCart(bookId, cartId);
        updateTotalMoneyForCart(cartId, 0);
    }
    
}
