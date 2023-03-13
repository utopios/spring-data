package com.cesi.springbases.controllers;

import com.cesi.springbases.domain.Book;
import com.cesi.springbases.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
* Pour créer un contrôleur API, il nous faut utiliser l'annotation @RestController, et le fonctionnement est sensiblement le même
* que pour le contrôleur MVC classique.
*
* Chaque route aura ainsi un endpoints/ et il nous faudra cette fois-ci, au lieu d'envoyer une vue peuplée via le modèle,
* directement une réponse au format JSON. Pour ce faire, on utilise le type générique ResponseEntity<T>
* */
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

    /*
    * Si l'on doit pouvoir réceptionner un body dans le JSON de la requête, alors il nous faut transformer ce JSON en un objet via l'annotation @RequestBody, dans le
    * but de pouvoir le traiter dans le code Java.
    * */

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
