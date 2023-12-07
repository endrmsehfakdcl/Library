package com.library.mapper;

import com.library.model.Book;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper {
    int insertBook(Book book);
    int updateBook(Book book);
    Book findBookById(Long bookId);
}
