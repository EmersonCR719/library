package com.app.library.service.dto;

import lombok.Data;

@Data
public class BookInDTO {

    private String title;
    private String author;
    private String isbn;
    private int publicationYear;
}
