package com.app.library.mapper;

import com.app.library.persistence.entity.Book;
import com.app.library.service.dto.BookInDTO;
import org.springframework.stereotype.Component;

@Component
public class BookInDTOToBook implements IMapper<BookInDTO, Book> {

    @Override
    public Book map(BookInDTO in) {
        Book book = new Book();
        book.setTitle(in.getTitle());
        book.setAuthor(in.getAuthor());
        book.setIsbn(in.getIsbn());
        book.setPublicationYear(in.getPublicationYear());
        return book;
    }
}
