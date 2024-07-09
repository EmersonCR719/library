package com.app.library.service;

import com.app.library.exceptions.BookExceptions;
import com.app.library.mapper.BookInDTOToBook;
import com.app.library.persistence.entity.Book;
import com.app.library.persistence.repository.BookRepository;
import com.app.library.service.dto.BookInDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookInDTOToBook mapper;

    public BookService(BookRepository bookRepository, BookInDTOToBook mapper) {
        this.bookRepository = bookRepository;
        this.mapper = mapper;
    }

    public Book save(BookInDTO bookInDTO) {
        Book book = mapper.map(bookInDTO);
        return bookRepository.save(book);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id) {
        Optional<Book> optionalBook =this.bookRepository.findById(id);
        if(optionalBook.isEmpty()) {
            throw new BookExceptions("Book not found", HttpStatus.NOT_FOUND);
        }
        return bookRepository.findById(id);
    }

    public void deleteById(Long id) {
        Optional<Book> optionalBook =this.bookRepository.findById(id);
        if(optionalBook.isEmpty()) {
            throw new BookExceptions("Book not found", HttpStatus.NOT_FOUND);
        }
        this.bookRepository.deleteById(id);
    }

    public void updateById(Long id, BookInDTO bookInDTO) {
        Optional<Book> optionalBook =this.bookRepository.findById(id);
        if(optionalBook.isEmpty()) {
            throw new BookExceptions("Book not found", HttpStatus.NOT_FOUND);
        }
        Book book = optionalBook.get();
        book.setTitle(bookInDTO.getTitle());
        book.setAuthor(bookInDTO.getAuthor());
        book.setIsbn(bookInDTO.getIsbn());
        this.bookRepository.save(book);
    }
}
