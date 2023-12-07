package com.library.controller;

import com.library.dto.BookDTO;
import com.library.exception.BookNotFoundException;
import com.library.exception.DataInsertionException;
import com.library.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/register")
    public ResponseEntity<?> registerBook(@Valid @RequestBody BookDTO bookDTO) {
        try {
            bookService.register(bookDTO);

            return ResponseEntity.ok().body("Book successfully registered.");
        } catch (DataInsertionException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("try later");
        }
    }

    @PatchMapping("/update/{bookId}")
    public ResponseEntity<?> updateBook(@PathVariable Long bookId, @RequestBody BookDTO bookDTO) {
        try{
            bookService.update(bookId, bookDTO);

            return ResponseEntity.ok().body("Book successfully updated.");
        } catch (BookNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (DataInsertionException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("try later");
        }
    }

}
