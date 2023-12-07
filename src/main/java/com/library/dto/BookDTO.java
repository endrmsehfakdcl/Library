package com.library.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {
    @NotEmpty(message = "title cannot be empty")
    private String title;
    @NotEmpty(message = "author cannot be empty")
    private String author;
    @NotEmpty(message = "publisher cannot be empty")
    private String publisher;
    @NotNull(message = "Price cannot be null")
    @Min(value = 0, message = "Price minimum 0")
    private Integer price;
}
