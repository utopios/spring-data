package com.cesi.springbases.services;

import com.cesi.springbases.domain.Book;

import java.util.List;

/*
* Pour pouvoir par la suite utiliser nos données dans un contrôleur MVC, il est de bon ton de réaliser un service.
*
* Ce service sera la réelle instance qui va envoyer les données au contrôleur. Il aura donc besoin de son côté de
* l'accès au Repository.
* */
public interface BookService {
    List<Book> findAll();
    Book findById(Long id);
    void save(Book book);
    void deleteById(Long id);

    Book findByName(String name);
}
