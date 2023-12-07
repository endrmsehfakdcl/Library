package com.library.mapper;

import com.library.model.BookLoan;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookLoanMapper {
    int insertBookLoan(BookLoan bookLoan);
    int updateBookLoanReturn(Long bookId);
    BookLoan findBookLoanByBookId(Long bookId);
    List<BookLoan> findBookLoanListByBookId(Long bookId);
}
