package com.library.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
    private Long bookId;
    private String title;
    private String author;
    private String publisher;
    private Integer price;
}
