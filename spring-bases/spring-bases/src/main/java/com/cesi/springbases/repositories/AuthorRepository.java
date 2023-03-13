package com.cesi.springbases.repositories;

import com.cesi.springbases.domain.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
