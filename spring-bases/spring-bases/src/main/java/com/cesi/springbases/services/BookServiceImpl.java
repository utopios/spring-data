package com.cesi.springbases.services;

import com.cesi.springbases.domain.Book;
import com.cesi.springbases.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/*
* Pour pouvoir créer un service réel, il nous faut faire une classe qui va avoir comme annotation @Service.
*
* Via cette annotation, Spring saura que l'élément est un service au sens où il est généré et envoyé à nos futurs contrôleurs
* par Spring. De la sorte, nous réalisons ce qu'on appelle une injection de dépendance et une inversion de contrôle.
* */

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        // Ici, on va chercher dans le repository tous les éléments, qui sont envoyés sous la forme d'un Iterable.

        // Pour en avoir une version en Liste, il suffit de caster l'Iterable en List via la syntaxe suivante
        return (List<Book>) bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        // Pour n'avoir qu'un seul élément, on recherche dans le repository par ID. Si l'on ne trouve rien, on enverra 'null'
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Book book) {
        // Pour la sauvegarde, on va simplement déléguer à notre repository la fonctionnalité de sauvegarde

        // Cette feature sert à la fois pour l'ajout et la modification:
        // - Dans le cas où le livre n'a pas d'ID, il sera ajouté
        // - Dans le cas où il en a un, alors c'est l'élément à tel ID qui se verra modifié
        bookRepository.save(book);
    }

    @Override
    public void deleteById(Long id) {
        // Pour la suppression, il s'agit d'une simple délégation de la fonctionnalité.
        bookRepository.deleteById(id);
    }

    @Override
    public Book findByName(String name) {
        return bookRepository.findByTitle(name);
    }

    @Override
    public Set<Book> findAllByTitle(String title) {
        return bookRepository.findAllByTitleLike(title);
    }
}
