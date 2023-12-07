package com.library.controller;

import com.library.dto.BookLoanDTO;
import com.library.exception.DataInsertionException;
import com.library.model.BookLoan;
import com.library.service.BookLoanService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookloan")
@RequiredArgsConstructor
public class BookLoanController {
    private final BookLoanService bookLoanService;

    @PostMapping
    public ResponseEntity<?> bookLoan(@RequestBody BookLoanDTO bookLoanDTO){
        try {
            bookLoanService.bookLoan(bookLoanDTO);

            return ResponseEntity.ok().body("Book successfully loaned.");
        } catch (DataInsertionException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PatchMapping("/return")
    public ResponseEntity<?> bookReturn(@RequestBody BookLoanDTO bookLoanDTO) {
        try{
            bookLoanService.bookLoanReturn(bookLoanDTO);

            return ResponseEntity.ok().body("Book successfully returned");
        } catch (DataInsertionException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/record/{bookId}")
    public ResponseEntity<?> getBookLoanList(@PathVariable Long bookId) {
        try{
            List<BookLoan> BookLoanList = bookLoanService.getBookLoanList(bookId);

            return ResponseEntity.ok().body(BookLoanList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
