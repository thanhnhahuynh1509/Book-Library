package com.manager.booklibrary.controller;

import com.manager.booklibrary.entity.Cart;
import com.manager.booklibrary.service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    
    @Autowired
    private CartService cartService;

    @GetMapping("/{cartId}")
    public ResponseEntity<?> getCart(@PathVariable long cartId) {
        Cart cart = cartService.getCart(cartId);
        return ResponseEntity.ok().body(cart);
    }

    @PutMapping("/{cartId}/books/{bookId}")
    public ResponseEntity<?> addBookIntoCart(@PathVariable long cartId, @PathVariable long bookId) {
        cartService.addBookIntoCart(bookId, cartId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{cartId}/books/{bookId}")
    public ResponseEntity<?> deleteBookOutCart(@PathVariable long cartId, @PathVariable long bookId) {
        cartService.deleteBookOutCart(bookId, cartId);
        return ResponseEntity.ok().build();
    }
}
