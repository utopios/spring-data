package com.cesi.springbases.bootstrap;

import com.cesi.springbases.domain.Author;
import com.cesi.springbases.domain.Book;
import com.cesi.springbases.repositories.AuthorRepository;
import com.cesi.springbases.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/*
* Si l'on veut pouvoir avoir des éléments directement dans notre application à son lancement, il est possible d'initialiser les
* données via l'utilisation d'un CommandLineRunner. Cette interface va être implémentée par notre classe, et sa méthode run() sera exécutée
* au lancement à condition que celui-ci soit un élément Spring valide. Pour ce faire, il faut qu'il soit un @Component
* */

@Component
public class BootstrapData implements CommandLineRunner {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BootstrapData(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // On va créer un nouveau livre
        Book tgc = new Book();

        // Lui instancier ses champs
        tgc.setIsbn("123-147852-369");
        tgc.setTitle("The Golden Compass");
        tgc.setDescription("Livre jeunesse parlant d'une fille perdue dans ses pensées...");

        // Création d'un auteur
        Author philip = new Author();

        // On instancie les champs de l'auteur
        philip.setFirstName("Philip");
        philip.setLastName("PULLMAN");

        // On l'ajoute à notre base de données avec la méthode .save() et on récupère en sortie une version avec ID
        Book tgcSaved = bookRepository.save(tgc);
        Author philipSaved = authorRepository.save(philip);

        tgcSaved.getAuthors().add(philipSaved);
        philipSaved.getBooks().add(tgcSaved);

        bookRepository.save(tgcSaved);
        authorRepository.save(philipSaved);

        // On peut afficher le nombre de livres dans notre BdD
        System.out.println("\n=== Bootstrap Data ===");
        System.out.println("Book count: " + bookRepository.count());
        System.out.println("Author count: " + authorRepository.count());

        System.out.println("\n=== Books from Philip PULLMAN ===");
        for (Book b : philipSaved.getBooks()) {
            System.out.println(b.toString());
        }
    }
}
