package com.cesi.springbases.controllers;

import com.cesi.springbases.domain.Book;
import com.cesi.springbases.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
* Pour créer un contrôleur Java Spring, il nous faut utiliser l'annotation @Controller
*
* Pour pouvoir provoquer l'accès à ce contrôleur et à ses méthodes via une route particulière, on peut utiliser le @RequestMapping
*
* Ce RequestMapping va prendre en paramètre une chaine de caractère étant la route vers ce contrôleur
* */
@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /*
    * Pour obtenir une route '/books' de type Get, on créé une méthode dont le but sera la population du
    * modèle de données et l'envoi d'une vue Thymeleaf dont le nom sera 'bookList'. Cette vue devra se trouver dans
    * le dossier ressources/templates et porter le même nom que spécifié ici.
    * */
    @GetMapping
    public String getBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "bookList";
    }

    @GetMapping("/create")
    public String createBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "createBook";
    }

    /*
    * Pour les méthodes gérant des données entrantes, on va devoir récupérer l'objet envoyé par notre formulaire après sa validation par
    * les annotations @Validated. L'objet pourra ainsi être traité au niveau de notre code Java avant le renvoie d'une page existante via la redirection.
    * */
    @PostMapping("/create")
    public String createBook(@Validated Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "createBook";
        }
        bookService.save(book);
        return "redirect:/books";
    }

    /*
    * Dans le cas où notre route demande une variable dynamique, alors la syntaxe est la suivante :chemin/{nom_variable}
    * Cette variable sera récupérable via l'annotation @PathVariable et pasage entre les parenthèses du même nom que précisé dans le
    * mapping du chemin
    *
    * */

    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable("id") Long id, Model model) {
        Book bookFound = bookService.findById(id);

        if (bookFound != null) {
            model.addAttribute("book", bookFound);
            return "editBook";
        } else {
            return "redirect:/books";
        }
    }

    @GetMapping("/{name}")
    public String getBook(@PathVariable("name") String name, Model model) {
        Book bookFound = bookService.findByName(name);

        if (bookFound != null) {
            model.addAttribute("book", bookFound);
            return "book";
        } else {
            return "redirect:/books";
        }
    }

    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long id, @Validated Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "editBook";
        }
        book.setId(id);
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }
}
