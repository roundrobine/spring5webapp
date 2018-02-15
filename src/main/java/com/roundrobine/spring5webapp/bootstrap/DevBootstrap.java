package com.roundrobine.spring5webapp.bootstrap;

import com.roundrobine.spring5webapp.model.Author;
import com.roundrobine.spring5webapp.model.Book;
import com.roundrobine.spring5webapp.model.Publisher;
import com.roundrobine.spring5webapp.repositories.AuthorRepository;
import com.roundrobine.spring5webapp.repositories.BookRepository;
import com.roundrobine.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){

        Publisher p1 = new Publisher("Dimo Iliev", "Kej na Revolucijata 3/A");
        publisherRepository.save(p1);
        //Eric
        Author eric = new Author("Eric", "Evans");

        Book ddd = new Book("Domain Driven Design", "1234", p1);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        Publisher p2 = new Publisher("Joana Veljanoska", "Vanco Pitosheski 5");
        publisherRepository.save(p2);
        //Rod
        Author rod = new Author("Rod", "Johnson");

        Book noEJB = new Book("J2EE Development without EJB", "23444", p2 );
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);


    }
}
