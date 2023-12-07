package com.library.service;

import com.library.dto.BookDTO;
import com.library.exception.BookNotFoundException;
import com.library.exception.DataInsertionException;
import com.library.mapper.BookMapper;
import com.library.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookMapper bookMapper;

    @Transactional
    public void register(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPublisher(bookDTO.getPublisher());
        book.setPrice(bookDTO.getPrice());

        try {
            int isInserted = bookMapper.insertBook(book);
            if (isInserted == 0) {
                throw new DataInsertionException("Fail to insert book data: " + bookDTO.getTitle());
            }
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error occurred during book registration: " + e.getMessage());
        }
    }

    @Transactional
    public void update(Long bookId, BookDTO bookDTO) {
        Book isBookExists = bookMapper.findBookById(bookId);
        if (isBookExists == null) {
            throw new BookNotFoundException(bookId);
        }

        isBookExists.setTitle(bookDTO.getTitle());
        isBookExists.setAuthor(bookDTO.getAuthor());
        isBookExists.setPublisher(bookDTO.getPublisher());
        isBookExists.setPrice(bookDTO.getPrice());

        try{
            int isUpdated = bookMapper.updateBook(isBookExists);
            if (isUpdated == 0) {
                throw new DataInsertionException("Fail to update book data: " + bookId);
            }
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error occurred during book update: " + e.getMessage());
        }
    }

}
