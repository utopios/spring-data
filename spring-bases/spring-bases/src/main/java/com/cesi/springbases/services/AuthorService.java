package com.cesi.springbases.services;

import com.cesi.springbases.domain.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
    Author findById(Long id);
    void save(Author author);
    void deleteById(Long id);
}
