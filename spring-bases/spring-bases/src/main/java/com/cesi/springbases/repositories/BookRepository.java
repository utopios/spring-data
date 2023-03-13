package com.cesi.springbases.repositories;

import com.cesi.springbases.domain.Book;
import org.springframework.data.repository.CrudRepository;

/*
* Pour pouvoir par la suite manipuler nos modèles, il nous faut réaliser un repository dans le but de générer une
* abstraction de nos données et d'avoir accès à des méthodes comme .save(), .findAll(), etc...
*
* De la sorte, on pourra manipuler nos données sans avoir à entrer de requête SQL classique.
* */
public interface BookRepository extends CrudRepository<Book, Long> {
}
