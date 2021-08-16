package com.manager.booklibrary.service;

import com.manager.booklibrary.entity.Cart;
import com.manager.booklibrary.entity.Reader;
import com.manager.booklibrary.exception.NotFoundException;
import com.manager.booklibrary.repository.CartRepository;
import com.manager.booklibrary.repository.ReaderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReaderServiceImpl implements ReaderService {

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Reader getReader(long id) {
        boolean checkReaderExists = readerRepository.findById(id).isPresent();
        if (!checkReaderExists) {
            log.error("Get failed: not found Reader with id: " + id);
            throw new NotFoundException("Not found Reader with id: " + id);
        } else {
            log.info("Getting reader with id: " + id);
            return readerRepository.findById(id).get();
        }
    }

    @Override
    public void addReader(Reader reader) {
        long id = reader.getId();
        boolean checkReaderExists = readerRepository.findById(id).isPresent();
        if (checkReaderExists) {
            log.error("Saved fail: Reader is already");
            throw new NotFoundException("NReader is already");
        } else {
            log.info("Saving reader with id: " + id);
            Cart cart = new Cart(0l, 0);
            cartRepository.save(cart);
            reader.setCart(cart);
            readerRepository.save(reader);
        }
    }

    @Override
    public void updateReader(Reader reader) {
        long id = reader.getId();
        boolean checkReaderExists = readerRepository.findById(id).isPresent();
        if (!checkReaderExists) {
            log.error("Updated fail: not found Reader with id: " + id);
            throw new NotFoundException("Not found Reader with id: " + id);
        } else {
            log.info("Updating reader with id: " + id);
            readerRepository.save(reader);
        }
    }

    @Override
    public void deleteReader(long id) {
        boolean checkReaderExists = readerRepository.findById(id).isPresent();
        if (!checkReaderExists) {
            log.error("Deleted fail: not found Reader with id: " + id);
            throw new NotFoundException("Not found Reader with id: " + id);
        } else {
            log.info("Deleting reader with id: " + id);
            readerRepository.deleteById(id);
        }
    }

    @Override
    public Reader getReaderByIdUser(long id) {
        log.info("Getting reader with User id: " + id);
        return readerRepository.findReaderByIdUser(id);
    }
    
}
