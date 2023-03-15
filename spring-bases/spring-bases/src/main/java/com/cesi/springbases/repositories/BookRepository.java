package com.cesi.springbases.repositories;

import com.cesi.springbases.domain.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/*
* Pour pouvoir par la suite manipuler nos modèles, il nous faut réaliser un repository dans le but de générer une
* abstraction de nos données et d'avoir accès à des méthodes comme .save(), .findAll(), etc...
*
* De la sorte, on pourra manipuler nos données sans avoir à entrer de requête SQL classique.
* */
public interface BookRepository extends CrudRepository<Book, Long> {

    Book findByTitle(String title);

    Set<Book> findAllByTitleLike(String title);


    @Query("SELECT b FROM Book b WHERE b.title like :search OR b.description like :search")
    Set<Book> searchBooks(String search);
}
