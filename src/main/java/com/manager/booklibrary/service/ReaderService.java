package com.manager.booklibrary.service;

import com.manager.booklibrary.entity.Reader;

public interface ReaderService {
    
    Reader getReader(long id);
    
    Reader getReaderByIdUser(long id);

    void addReader(Reader reader);

    void updateReader(Reader reader);

    void deleteReader(long id);


}
