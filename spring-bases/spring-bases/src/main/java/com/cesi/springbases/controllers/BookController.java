package com.cesi.springbases.controllers;

import com.cesi.springbases.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    * Pour obtenir une route '/books' de type Get, on
    * */
    @GetMapping
    public String getBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "bookList";
    }
}
