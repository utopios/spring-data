package com.cesi.springbases.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/*
* Lorsque l'on veut travailler avec Spring, nos classes de modèle doivent être des entités
* du point de vue de Spring et / ou d'Hibernate. Pour ce faire, il va falloir ajouter l'annotation @Entity
* et respecter quelques contraintes
* */

@Entity
public class Book {

    /*
    * Pour que notre classe soit valide du point de vue de l'entité de données, il nous faut un ID. Cet id sera ici
    * un id numérique automatiquement généré.
    * */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;
    private String isbn;

    /*
    * Pour le ManyToMany, l'un des deux éléments va devoir informer Hibernate de quelles sont
    * les tables servant à cette jointure. Ici on demande :
    * - La table 'author_book'
    * - Une colonne 'book_id' pour obtenir le livre
    * - Une colonne 'author_id' pour obtenir l'auteur du livre
    * */
    @ManyToMany
    @JoinTable(name = "author_book", joinColumns = @JoinColumn(name = "book_id"),
    inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
