package com.cesi.springbases.domain;

import java.util.HashSet;
import java.util.Set;

public class Favoris {

    private Set<Book> books;

    public Favoris() {
        books = new HashSet<>();
    }
    public Set<Book> getBooks(){
        return books;
    }
}
