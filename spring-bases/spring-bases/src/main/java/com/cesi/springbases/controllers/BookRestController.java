package com.cesi.springbases.controllers;

import com.cesi.springbases.domain.Book;
import com.cesi.springbases.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookRestController {
    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> booksFound = bookService.findAll();
        return ResponseEntity.ok(booksFound);
    }

    @PostMapping
    public ResponseEntity<Book> postBook(@Validated @RequestBody Book book) {
        bookService.save(book);
        return ResponseEntity.ok(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> editBook(@PathVariable("id") Long id, @Validated @RequestBody Book book) {
        Book bookFound = bookService.findById(id);
        if (bookFound != null) {
            bookFound.setTitle(book.getTitle());
            bookFound.setDescription(book.getDescription());
            bookFound.setIsbn(book.getIsbn());
            bookService.save(bookFound);
            return ResponseEntity.ok(bookFound);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Long id) {
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
