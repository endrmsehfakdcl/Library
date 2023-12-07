package com.library.service;

import com.library.dto.BookLoanDTO;
import com.library.exception.DataInsertionException;
import com.library.exception.UserNotFoundException;
import com.library.mapper.BookLoanMapper;
import com.library.mapper.BookMapper;
import com.library.mapper.UserMapper;
import com.library.model.Book;
import com.library.model.BookLoan;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookLoanService {

    private final BookLoanMapper bookLoanMapper;
    private final UserMapper userMapper;
    private final BookMapper bookMapper;

    @Transactional
    public void bookLoan(BookLoanDTO bookLoanDTO) {
        Book isBookExists = bookMapper.findBookById(bookLoanDTO.getBookId());
        if (isBookExists == null) {
            throw new IllegalStateException("Book with bookId(" + bookLoanDTO.getBookId() + ") does not exist");
        }

        BookLoan currentBookLoan = bookLoanMapper.findBookLoanByBookId(bookLoanDTO.getBookId());
        if (currentBookLoan != null && currentBookLoan.isStatus()) {
            throw new IllegalStateException("Book is already loaned");
        }

        Long userId = userMapper.findUserIdByUsername(bookLoanDTO.getUsername());
        if (userId == null) {
            throw new UserNotFoundException(bookLoanDTO.getUsername());
        }

        BookLoan bookLoan = new BookLoan();
        bookLoan.setBooks_bookId(bookLoanDTO.getBookId());
        bookLoan.setUsers_userId(userId);
        bookLoan.setLoanDate(new Date());
        bookLoan.setStatus(true);

        try {
            int isInserted = bookLoanMapper.insertBookLoan(bookLoan);
            if (isInserted == 0) {
                throw new DataInsertionException("Fail to insert bookLoan data for bookId: " + bookLoanDTO.getBookId());
            }
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error occurred during book loan: " + e.getMessage());
        }
    }

    @Transactional
    public void bookLoanReturn(BookLoanDTO bookLoanDTO) {
        BookLoan bookLoan = bookLoanMapper.findBookLoanByBookId(bookLoanDTO.getBookId());
        if (bookLoan == null || !bookLoan.isStatus()) {
            throw new IllegalStateException("Book is not loaned");
        }

        bookLoan.setReturnDate(new Date());
        bookLoan.setStatus(false);

        try {
            int isUpdated = bookLoanMapper.updateBookLoanReturn(bookLoan.getBookLoanId());
            if (isUpdated == 0) {
                throw new DataInsertionException("Fail to insert bookLoan data for bookId: " + bookLoanDTO.getBookId());
            }
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error occurred during book loan return: " + e.getMessage());
        }
    }

    public List<BookLoan> getBookLoanList(Long bookId) {
        return bookLoanMapper.findBookLoanListByBookId(bookId);
    }
}
