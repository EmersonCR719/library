package com.app.library.controller;

import com.app.library.persistence.entity.Book;
import com.app.library.service.BookService;
import com.app.library.service.dto.BookInDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/bookstall")
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @PostMapping("/create")
    public Book createBook(@RequestBody BookInDTO bookInDTO) {
        return bookService.save(bookInDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable("id") Long id) {
        this.bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
        Optional<Book> book = bookService.findById(id);
        return book.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable("id") Long id, @RequestBody BookInDTO bookInDTO) {
        Optional<Book> book = bookService.findById(id);
        this.bookService.updateById(id, bookInDTO);
        return book.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
