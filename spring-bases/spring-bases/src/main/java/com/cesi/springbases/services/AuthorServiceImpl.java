package com.cesi.springbases.services;

import com.cesi.springbases.domain.Author;
import com.cesi.springbases.repositories.AuthorRepository;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return (List<Author>) authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
