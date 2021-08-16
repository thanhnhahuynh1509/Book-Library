package com.manager.booklibrary.service;

import com.manager.booklibrary.entity.Cart;

public interface CartService {
    
    Cart getCart(long id);

    void addBookIntoCart(long bookId, long cartId);

    void deleteBookOutCart(long bookId, long cartId);
}
